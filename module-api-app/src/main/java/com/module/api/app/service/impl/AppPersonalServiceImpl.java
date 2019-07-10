package com.module.api.app.service.impl;

import cn.hutool.core.util.ReUtil;
import com.module.api.app.mapper.AppPersonalMapper;
import com.module.api.app.result.UserManagerResult;
import com.module.api.app.service.AppPersonalService;
import com.module.common.ResponseCode;
import com.module.common.bean.AppCurrentUser;
import com.module.common.constant.RegexPattern;
import com.module.common.exception.DBException;
import com.module.common.exception.ValidationException;
import com.module.common.util.CheckSearchUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AppPersonalServiceImpl
 * @Description 个人信息
 * @Author YJT
 * @Date 2019/7/10 0010 9:36
 * @Version 1.0
 **/
public class AppPersonalServiceImpl implements AppPersonalService {


    @Resource
    private AppPersonalMapper appPersonalMapper;

    @Override
    public UserManagerResult personalMsg(Integer userId) {
        UserManagerResult personalMsg = appPersonalMapper.getPersonalMsg(userId);
        personalMsg.setTotal(personalMsg.getDfh()+personalMsg.getDfk()+personalMsg.getDsh()+personalMsg.getYwc());
        personalMsg.setPayStatus(personalMsg.getPayWord().length()>2?1:0);
        return personalMsg;
    }

    @Override
    public boolean updateUserName(String username, AppCurrentUser user) {
        if (!ReUtil.isMatch(RegexPattern.REGEX_USERNAME,username)){
            throw new ValidationException(ResponseCode.C_500003,"用户名格式不正确");
        }
        int updateStatus=0;
        if (!user.getUsername().equals(username.trim())){
            user.setUsername(username.trim());
            updateStatus=appPersonalMapper.updateUserName(user);
        }
        if (updateStatus>0){
            return true;
        }
        else  {
            throw new DBException(ResponseCode.C_520006);
        }
    }

    @Override
    public void updateHeadImg(String path, AppCurrentUser user) {
        appPersonalMapper.updateHeadImg(path,user.getId());
    }
}
