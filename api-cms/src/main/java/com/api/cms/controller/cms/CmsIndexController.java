package com.api.cms.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductResult;
import com.module.admin.cms.service.CmsBannerService;
import com.module.admin.cms.service.CmsNewsService;
import com.module.admin.cms.service.CmsProductService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@Api(tags = "首页")
@RestController
@RequestMapping("/cms/index")
public class CmsIndexController {
    @SofaReference
    private CmsBannerService cmsBannerService;
    @SofaReference
    private CmsProductService productService;
    @SofaReference
    private CmsNewsService cmsNewsService;

    @ApiOperation(value = "banner列表", notes = "只查询前十")
    @GetMapping("/bannerList")
    public Response<JSONObject> list() {
        //banner结果集
        IPage<CmsBanner> cmsBannerIPage = cmsBannerService.showList(new PageQuery(1, 10));
        //产品结果集
        IPage<CmsProduct> cmsProductIPage = productService.showList(new ProductQuery(1, 4));
        //热门产品
        CmsProduct hotProduct=productService.hotProduct();
        //新闻结果集
        IPage<CmsNews> cmsNews = cmsNewsService.showList(new PageQuery(1,4));
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("banners",cmsBannerIPage.getRecords());
        jsonObject.put("products",cmsProductIPage.getRecords());
        jsonObject.put("hotProduct",hotProduct);
        jsonObject.put("news",cmsNews.getRecords());
        return Response.success(jsonObject);
    }

    @ApiOperation(value = "搜索",notes = "搜索查询商品关键字")
    @GetMapping("/searchKeyWord")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord",paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "limit",paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "page",paramType = "query" ,required = true,value = "当前页数")
    })
    public Response<CmsProductResult> searchKeyWord(PageQuery query){
        return Response.success(productService.searchKeyWord(query));
    }


}
