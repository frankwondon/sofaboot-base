package com.module.api.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.constant.ProductConstant;
import com.module.api.app.dto.AppProductDto;
import com.module.api.app.entity.AppProduct;
import com.module.api.app.entity.AppProductAdditionOne;
import com.module.api.app.entity.AppProductSku;
import com.module.api.app.mapper.AppProductMapper;
import com.module.api.app.mapper.AppProductSkuMapper;
import com.module.api.app.query.AppProductQuery;
import com.module.api.app.service.AppProductService;
import com.module.common.ResponseCode;
import com.module.common.constant.ProductEnum;
import com.module.common.exception.DBOperationException;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @Author wangdong
 * @Date: 2019/6/14 11:33
 */
public class AppProductServiceImpl implements AppProductService {
    @Resource
    private AppProductMapper productMapper;
    @Resource
    private AppProductSkuMapper productSkuMapper;



    @Override
    public IPage<AppProduct> list(AppProductQuery pageQuery){
        Page page=new Page<>(pageQuery.getPage(),pageQuery.getLimit());
        return productMapper.list(page,pageQuery.getStatus(),pageQuery.getKeyWord());
    }

    @Override
    public String productNum(){
        String productNum = productMapper.callProductNum(1);
        ProductConstant.PRODUCT_CREATE_NUM.put(productNum,LocalDateTime.now());
        return productNum;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(AppProductDto productDto){
        String num = productDto.getNum();
        if (ProductConstant.PRODUCT_CREATE_NUM.get(num)==null){
            throw new DBOperationException(ResponseCode.C_510002);
        }
        try {
            //立即上架的商品前台直接可以查询到
            if (ProductEnum.ShelfType.ATONCE.key()==productDto.getShelfType()){
                productDto.setStatus(0);
            }else {
                productDto.setStatus(1);
            }
            if (finalSkuCheck(productDto.getSkus(),productDto.getFinalSkus())){
                //插入主表
                productMapper.insert(productDto);
                AppProductAdditionOne one=new AppProductAdditionOne();
                one.setProductId(productDto.getId());
                one.setPv(0);
                //插入附加表
                productMapper.insertAppProductAdditionOne(one);
                saveSku(productDto.getProductSkus(),productDto.getId());
            }
        }finally {
            ProductConstant.PRODUCT_CREATE_NUM.remove(num);
        }
    }

    /**
     * 验证前端传过来的Sku正确性
     * @param finalSku
     * @return
     */
    private boolean finalSkuCheck(String skus,String finalSku){
       return true;
    }

    /**
     * 保存SKU数据
     * @param productSkus
     */
    private void saveSku(List<AppProductSku> productSkus,Integer productId){
        productSkus.forEach(o -> {
            o.setProductId(productId);
            productSkuMapper.insert(o);
        });
    }

    @Override
    public AppProductDto findUpdateDetail(Integer productId){
        AppProduct appProduct = productMapper.selectById(productId);
        AppProductDto detailResult=new AppProductDto();
        BeanUtils.copyProperties(appProduct,detailResult);
        List<AppProductSku> appProductSkus = productSkuMapper.listProductSku(productId);
        detailResult.setProductSkus(appProductSkus);
        return detailResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppProductDto productDto){
        //立即上架的商品前台直接可以查询到
        if (ProductEnum.ShelfType.ATONCE.key()==productDto.getShelfType()){
            productDto.setStatus(0);
        }else {
            productDto.setStatus(1);
        }
        //查看修改之前的SKU有没有变动
        AppProduct appProduct = productMapper.selectById(productDto.getId());
        if (appProduct.getFinalSkus().equals(productDto.getFinalSkus())){
            return;
        }
        if (finalSkuCheck(productDto.getSkus(),productDto.getFinalSkus())){
            //删除之前的sku
            productSkuMapper.delProductSku(productDto.getId());
            productMapper.updateById(productDto);
            saveSku(productDto.getProductSkus(),productDto.getId());
        }
    }


    @Override
    public void shelfAndObtained(Integer productId, Integer status){
        if (ProductEnum.ShelfStatus.SHELF.key()==status|| ProductEnum.ShelfStatus.OBTAINED.key()==status){
            productMapper.updateShelfAndObtained(productId,status);
        }else {
            throw new DBOperationException(ResponseCode.C_500003);
        }
    }

    @Override
    public List<AppProduct> likeSearchProduct(String keyWord) {
        return null;
    }
}
