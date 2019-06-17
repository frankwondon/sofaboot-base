package com.api.cms;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductTypeResult;
import com.module.common.ResponseCode;
import com.module.common.bean.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonTest {



    @Test
    public void test1() {
        ResponseCode[] values = ResponseCode.values();
        for (ResponseCode value : values) {
            System.out.println("code："+value.code()+"   msg："+value.cnMsg());
        }
    }

    @Test
    public void test2(){

    }


}
