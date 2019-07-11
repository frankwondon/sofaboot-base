package com.module.api.app.service.impl;

import cn.hutool.core.util.ReUtil;
import com.module.api.app.mapper.AppMineMapper;
import com.module.api.app.mapper.UserMapper;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.service.AppMineService;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.constant.RegexPattern;
import com.module.common.exception.DBException;
import com.module.common.exception.ValidationException;
import com.sun.xml.internal.ws.util.Pool;

import javax.annotation.Resource;

/**
 * @ClassName AppMineServiceImpl
 * @Description 我的 界面  逻辑层
 * @Author YJT
 * @Date 2019/7/10 0010 9:04
 * @Version 1.0
 **/

public class AppMineServiceImpl implements AppMineService {
    @Resource
    private AppMineMapper appMineMapper;

    @Override
    public boolean sendAdvice(SendAdviceQuery query) {
        //todo  意见文本内容敏感信息
        if (1!=1){
            throw new DBException(ResponseCode.C_520009,"反馈内容敏感");
        }
        if (!ReUtil.isMatch(RegexPattern.MOBILE, query.getCellPhone())) {
            throw new ValidationException(ResponseCode.C_500003, "手机号格式错误");
        }
        int a=0;
        a=appMineMapper.sendAdvice(query);
        if (a>0){
            return true;
        }else {
            throw new DBException(ResponseCode.C_520008,"意见反馈不成功");
        }

    }
}
