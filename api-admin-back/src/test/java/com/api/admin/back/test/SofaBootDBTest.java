package com.api.admin.back.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.service.BackUserService;
import com.module.common.constant.BackAdminConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootDBTest {
    @SofaReference
    BackUserService backUserService;
    @Test
    public void sayHello(){
        BackUser backUser=new BackUser();
        backUser.setCellPhoneNum("15001200836");
        backUser.setPassword("123456");
        backUserService.insertUser(backUser);
    }


    @Test
    public void createUser(){
        BackUser backUser=new BackUser();
        backUser.setCellPhoneNum("18888888888");
        backUser.setUsername("superadmin");
        backUser.setPassword("123456");
        backUser.setUserType(BackAdminConstant.USER_TYPE_NORMAL);
        backUser.setLocked(0);
        backUser.setCreateBy(-1);
        backUserService.insertUser(backUser);
    }
}
