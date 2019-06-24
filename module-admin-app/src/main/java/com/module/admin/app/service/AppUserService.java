package com.module.admin.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.result.UserManagerResult;
import com.module.common.bean.PageQuery;

/**
 * @author wangdong
 * @date: 2019/6/21 11:21
 */
public interface AppUserService {
    /**
     * 分页查询用户列表
     * @param query
     * @return
     */
    IPage<UserManagerResult> listOfUser(PageQuery query);


    /**
     * 禁用用户
     * @param id
     */
    void disable(Integer id);

}
