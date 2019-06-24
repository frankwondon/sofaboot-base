package com.module.admin.app.service.impl;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.constant.ProductConstant;
import com.module.admin.app.dto.AppProductDto;
import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.entity.AppProductAdditionOne;
import com.module.admin.app.entity.AppProductSku;
import com.module.admin.app.mapper.AppProductMapper;
import com.module.admin.app.mapper.AppProductSkuMapper;
import com.module.admin.app.query.AppProductQuery;
import com.module.admin.app.result.AppProductResult;
import com.module.admin.app.service.AppProductService;
import com.module.common.ResponseCode;
import com.module.common.constant.BackAdminConstant;
import com.module.common.constant.DateFormatConstant;
import com.module.common.constant.ProductEnum;
import com.module.common.exception.DBOperationException;
import com.module.common.util.JDK8DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author wangdong
 * @Date: 2019/6/14 11:33
 */
public class AppProductServiceImpl implements AppProductService {
    @Resource
    private AppProductMapper productMapper;
    @Resource
    private AppProductSkuMapper productSkuMapper;


    @Override
    public IPage<AppProductResult> list(AppProductQuery pageQuery) {
        Page page = new Page<>(pageQuery.getPage(), pageQuery.getLimit());
        if (pageQuery.getStatus() == ProductEnum.SearchShelfStatus.COUNTDOWN.key()) {
            return productMapper.listOfBuyStatus(page, pageQuery.getKeyWord(), BackAdminConstant.PRODUCT_COUNT_DOWN);
        } else {
            return productMapper.listOfShelf(page, pageQuery.getStatus(), pageQuery.getKeyWord());
        }
    }

    @Override
    public String productNum() {
        String productNum = productMapper.callProductNum(1);
        ProductConstant.PRODUCT_CREATE_NUM.put(productNum, LocalDateTime.now());
        return productNum;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProduct(AppProductDto productDto, Integer createUser) {
        String num = productDto.getNum();
        if (ProductConstant.PRODUCT_CREATE_NUM.get(num) == null) {
            throw new DBOperationException(ResponseCode.C_510002);
        }
        try {
            //立即上架的商品前台直接可以查询到
            if (ProductEnum.ShelfType.ATONCE.key() == productDto.getShelfType()) {
                productDto.setStatus(0);
            } else {
                productDto.setStatus(1);
            }
            //定时上架的商品
            if (ProductEnum.ShelfType.SCHEDULE.key() == productDto.getShelfType()) {
                //转换时间
                if (StrUtil.isBlank(productDto.getAutoShelfTimeStr())) {
                    throw new DBOperationException(ResponseCode.C_510005);
                }
                productDto.setAutoShelfTime(JDK8DateTimeUtil.parseOfStr(productDto.getAutoShelfTimeStr(), DateFormatConstant.DATE_TIME));
            }
            productDto.setCreateBy(createUser);
            productDto.setCreateTime(LocalDateTime.now());
            productDto.setSkuMd5(finalSkuCreate(productDto.getProductSkus()));
            //插入主表
            productMapper.insert(productDto);
            AppProductAdditionOne one = new AppProductAdditionOne();
            one.setProductId(productDto.getId());
            one.setPv(0);
            //插入附加表
            productMapper.insertAppProductAdditionOne(one);
            saveSku(productDto.getProductSkus(), productDto.getId());
        } finally {
            ProductConstant.PRODUCT_CREATE_NUM.remove(num);
        }
    }

    /**
     * 生成最终SKU的字符串MD5
     *
     * @param productSkus
     * @return
     */
    private String finalSkuCreate(List<AppProductSku> productSkus) {
        String str = JSON.toJSON(productSkus).toString();
        return SecureUtil.md5(str);
    }

    /**
     * 保存SKU数据
     *
     * @param productSkus
     */
    private void saveSku(List<AppProductSku> productSkus, Integer productId) {
        productSkus.forEach(o -> {
            o.setProductId(productId);
            productSkuMapper.insert(o);
        });
    }

    @Override
    public AppProductDto findUpdateDetail(Integer productId) {
        AppProduct appProduct = productMapper.selectById(productId);
        AppProductDto detailResult = new AppProductDto();
        BeanUtils.copyProperties(appProduct, detailResult);
        List<AppProductSku> appProductSkus = productSkuMapper.listProductSku(productId);
        detailResult.setProductSkus(appProductSkus);
        return detailResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppProductDto productDto) {
        //立即上架的商品前台直接可以查询到
        if (ProductEnum.ShelfType.ATONCE.key() == productDto.getShelfType()) {
            productDto.setStatus(0);
        } else {
            productDto.setStatus(1);
        }
        //查看修改之前的SKU有没有变动
        AppProduct appProduct = productMapper.selectById(productDto.getId());
        String md5Sku = finalSkuCreate(productDto.getProductSkus());
        if (!appProduct.getSkuMd5().equals(md5Sku)) {
            //删除之前的sku
            productDto.setSkuMd5(md5Sku);
            productSkuMapper.delProductSku(productDto.getId());
            saveSku(productDto.getProductSkus(), productDto.getId());
        }
        productMapper.updateById(productDto);
    }


    @Override
    public void shelfAndObtained(Integer productId, Integer status) {
        if (ProductEnum.ShelfStatus.SHELF.key() == status || ProductEnum.ShelfStatus.OBTAINED.key() == status) {
            productMapper.updateShelfAndObtained(productId, status);
        } else {
            throw new DBOperationException(ResponseCode.C_500003);
        }
    }

    @Override
    public List<AppProduct> likeSearchProduct(String keyWord) {
        return productMapper.likeSearchProduct(keyWord);
    }

    @Override
    public Integer productCountDown() {
        return productMapper.countOfBuyStatus(BackAdminConstant.PRODUCT_COUNT_DOWN);
    }
}
