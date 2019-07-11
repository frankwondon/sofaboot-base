package com.module.api.app.service;

import com.module.api.app.query.UpdatePayPwdQuery;
import com.module.api.app.result.UserManagerResult;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.bean.AppCurrentUser;

/**
 * @ClassName AppPersonalService
 * @Description 个人信息
 * @Author YJT
 * @Date 2019/7/10 0010 9:36
 * @Version 1.0
 **/
public interface AppPersonalService {
    /**
     * 获取登陆用户的信息
     * @param userId
     * @return
     */
   UserManagerResult personalMsg(Integer userId);

    /**
     * 修改用户名
     * @param username
     * @param user
     * @return
     */
    boolean updateUserName(String username, AppCurrentUser user);

    /**
     * 修改头像
     * @param path
     * @param user
     */
    void updateHeadImg(String path, AppCurrentUser user);

    /**
     * 修改用户名
     * @param mobile
     */
     void  sendPayCode(String mobile);

    /**
     * 修改支付密码
     * @param query
     * @return
     */
     boolean updateUserPayPwd(UpdatePayPwdQuery query);
}
