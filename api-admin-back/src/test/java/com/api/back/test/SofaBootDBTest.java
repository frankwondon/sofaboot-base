package com.api.back.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.entity.AppBanner;
import com.module.admin.app.result.AppBannerResult;
import com.module.admin.app.service.AppBannerService;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.query.BackUserQuery;
import com.module.admin.back.service.BackUserService;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.mapper.CmsNewsMapper;
import com.module.admin.cms.service.CmsNewsService;
import com.module.common.bean.PageQuery;
import com.module.common.constant.BackAdminConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootDBTest {
    @SofaReference
    BackUserService backUserService;

    @SofaReference
    AppBannerService bannerService;

    @Resource
    private CmsNewsMapper newsMapper;

    @Test
    public void list(){
        CmsNews cmsNews=new CmsNews();
        cmsNews.setCreateTime(LocalDateTime.now());
        newsMapper.insert(cmsNews);
    }


    @Test
    public void createUser(){
        BackUser backUser=new BackUser();
        backUser.setCellPhoneNum("18888888888");
        backUser.setUsername("superadmin");
        backUser.setUserType(BackAdminConstant.USER_TYPE_NORMAL);
        backUser.setLocked(0);
        backUser.setCreateBy(-1);
        backUser.setCreateTime(LocalDateTime.now());
        backUserService.insertUser(backUser);
    }

    @Test
    public void test1(){
        bannerService.list(new PageQuery(1, 10));
    }
}
