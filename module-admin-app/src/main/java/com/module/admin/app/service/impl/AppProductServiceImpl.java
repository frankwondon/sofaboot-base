package com.module.admin.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.dto.AppProductDto;
import com.module.admin.app.dto.SkuDto;
import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.entity.AppProductSku;
import com.module.admin.app.mapper.AppProductMapper;
import com.module.admin.app.mapper.AppProductSkuMapper;
import com.module.admin.app.query.AppProductQuery;
import com.module.admin.app.service.AppProductService;
import com.module.common.bean.PageQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 * @Author wangdong
 * @Date: 2019/6/14 11:33
 */
public class AppProductServiceImpl implements AppProductService {
    @Resource
    private AppProductMapper appProductMapper;
    @Resource
    private AppProductSkuMapper productSkuMapper;


    public IPage<AppProduct> list(AppProductQuery pageQuery){
        Page page=new Page<>(pageQuery.getPage(),pageQuery.getLimit());
        return appProductMapper.list(page,pageQuery.getStatus(),pageQuery.getKeyWord());
    }

    public String productNum(){
        return null;
    }


    @Transactional
    public void create(AppProductDto productDto){
        if (finalSkuCheck(productDto.getSkus(),productDto.getFinalSku())){
            saveSku(productDto.getFinalSku());
        }
        appProductMapper.insert(productDto);
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
     * @param finalSku
     */
    private void saveSku(String finalSku){
        JSONArray skus=JSONArray.parseArray(finalSku);
        skus.forEach(o -> {
            JSONObject object= (JSONObject) o;
            SkuDto skuDto = object.toJavaObject(SkuDto.class);
            productSkuMapper.insert(skuDto);
        });
    }


    public void update(){

    }
}
