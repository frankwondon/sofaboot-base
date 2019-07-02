package com.module.api.app.service.impl;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.dto.AppProductSkuDto;
import com.module.api.app.entity.AppProductSku;
import com.module.api.app.mapper.ProductMapper;
import com.module.api.app.mapper.ProductSkuMapper;
import com.module.api.app.query.ProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;
import com.module.api.app.service.ProductService;
import com.module.base.common.constant.RedisPrefix;
import lombok.extern.slf4j.Slf4j;
import com.module.common.bean.PageQuery;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdong
 * @date: 2019/6/25 10:20
 */
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private ProductSkuMapper productSkuMapper;

    @Override
    public String realTimeGoldPrice() {
        LocalDateTime startTime = LocalDateTime.now().withHour(9).withMinute(0).withSecond(0);
        LocalDateTime endTime = LocalDateTime.now().withHour(15).withMinute(30).withSecond(0);
        if (LocalDateTime.now().isAfter(startTime) && LocalDateTime.now().isBefore(endTime)) {
            RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.REAL_TIME_GOLD_PRICE.key());
            if (bucket.isExists()) {
                return bucket.get();
            }else {
                String goldPrice = getGoldPrice();
                bucket.set(goldPrice);
                bucket.expire(5, TimeUnit.MINUTES);
                return goldPrice;
            }
        } else {
            RBucket<String> bucket = redissonClient.getBucket(RedisPrefix.CACHE_GOLD_PRICE.key());
            if (bucket.isExists()){
                return bucket.get();
            }else {
                String goldPrice = getGoldPrice();
                bucket.set(goldPrice);
                return goldPrice;
            }
        }
    }


    private static String getGoldPrice(){
        try {
            HttpResponse execute = HttpRequest.post("https://www.sge.com.cn/graph/quotations").form("instid", "Au99.99").execute();
            JSONObject object = JSONObject.parseObject(execute.body());
            JSONArray data = object.getJSONArray("data");
            return data.getString(data.size() - 1);
        } catch (HttpException e) {
            log.error("请求金价时发生异常",e);
        }
        return "0.00";
    }

    @Override
    public List<AppProductResult> listProduct(ProductQuery query) {
        Page page = new Page<>(query.getPage(), query.getLimit());
        return productMapper.pageQuery(page, query.getTypeId());
    }

    @Override
    public List<AppProductTypeResult> listType(Integer limit) {
        List<AppProductTypeResult> appProductTypeResults = productMapper.listProductType();
        if (appProductTypeResults != null) {
            AppProductTypeResult appProductTypeResult = appProductTypeResults.get(0);
            Page page = new Page<>(1, limit);
            appProductTypeResult.setFirstDefaultPage(productMapper.pageQuery(page, appProductTypeResult.getTypeId()));
        }
        return appProductTypeResults;
    }

    @Override
    public List<AppProductResult> searchKeyWord(String keyWord){
        if (keyWord!=null&&!"".equals(keyWord.trim())) {
            List<AppProductResult> appProductResultList = productMapper.searchKeyWord("%" + keyWord + "%");
            return appProductResultList;
        }
        return null;
    }

    @Override
    public AppProductResult getProductById(Integer productId) {
        return productMapper.getProductById(productId);
    }


    /**
     * 精美
     * @return
     */
    @Override
    public List<AppProductResult> competitiveList() {
        List<AppProductResult> appProductResultList = productMapper.competitiveList();
        for (AppProductResult appProductResult : appProductResultList) {
            Integer productId = appProductResult.getProductId();
            appProductResult.setAppProductSku(productSkuMapper.productSkuByIdOne(productId));
        }
        return appProductResultList;
    }


    /**
     * 奢华
     * @return
     */
    @Override
    public List<AppProductResult> luxuriousList() {
        return productMapper.luxuriousList();
    }

    @Override
    public List<AppProductResult> casualList() {
        List<AppProductResult> appProductResultList = productMapper.casualList();
        for (AppProductResult appProductResult : appProductResultList) {
            Integer productId = appProductResult.getProductId();
            List<AppProductSkuDto> skuByProductId = getSkuByProductId(productId);
            appProductResult.setAppProductSkuList(skuByProductId);
        }
        return appProductResultList;
    }





    /**
     *  通过id 返回该商品库存信息 多模板
     * @param productId
     * @return
     */
    private List<AppProductSkuDto> getSkuByProductId(Integer productId){
        List<AppProductSkuDto> appProductSkuDtoList = productSkuMapper.productSkuById(productId);
        if (appProductSkuDtoList!=null&&appProductSkuDtoList.size()>0){
            return appProductSkuDtoList;
        }
        return null;
    }


}
