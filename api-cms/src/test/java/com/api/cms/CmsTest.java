package com.api.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductTypeResult;
import com.module.admin.cms.service.CmsBannerService;
import com.module.admin.cms.service.CmsNewsService;
import com.module.admin.cms.service.CmsProductService;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.common.bean.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsTest {

    @SofaReference
    CmsBannerService cmsBannerService;
    @SofaReference
    CmsProductService cmsProductService;
    @SofaReference
    CmsNewsService cmsNewsService;

    @SofaReference
    CmsProductTypeService cmsProductTypeService;

    @Test
    public void test1() {
        //banner结果集
        IPage<CmsBanner> cmsBannerIPage = cmsBannerService.showList(new PageQuery(1, 10));
        //产品结果集
        IPage<CmsProduct> cmsProductIPage = cmsProductService.showList(new ProductQuery(1, 4));
        //热门产品
        CmsProduct hotProduct = cmsProductService.hotProduct();
        //新闻结果集
        IPage<CmsNews> cmsNews = cmsNewsService.showList(new PageQuery(1, 4));
        System.out.println(1);
    }

    @Test
    public void test2(){
        ProductQuery query=new ProductQuery(1,10);
        List<CmsProductTypeResult> cmsProductTypeResults = cmsProductTypeService.showList(query);
        System.out.println(1);
    }


}
