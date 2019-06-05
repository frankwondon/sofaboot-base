package com.api.back.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.cms.service.CmsBannerService;
import com.module.common.bean.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsTest {

    @SofaReference
    CmsBannerService cmsBannerService;

    @Test
    public void test1(){
    }



}
