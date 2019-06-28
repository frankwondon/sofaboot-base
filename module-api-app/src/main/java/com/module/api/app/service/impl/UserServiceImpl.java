package com.module.api.app.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.dto.UserDto;
import com.module.api.app.entity.AppUser;
import com.module.api.app.mapper.UserMapper;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.service.UserService;
import com.module.base.common.constant.RedisPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;
import com.module.base.common.service.SMSSendService;
import com.module.common.ResponseCode;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;
import com.module.common.exception.DBException;
import com.module.common.util.AppTokenUtil;
import com.module.common.util.RandomUtil;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

/**
 * @author wangdong
 * @date: 2019/6/25 14:26
 */
public class UserServiceImpl implements UserService {
    @Resource
    private SMSSendService smsSendService;
    @Resource
    private UserMapper userMapper;
    @SofaReference
    private RedissonClient redissonClient;

    @Override
    public String getToken(AppTokenDto dto) {
        String token = null;
        if (dto.getAppUserType().equals(AppUserType.USER)) {
            //用户token用手机号+设备ID组合
            RMap<String, String> tokens = redissonClient.getMap(RedisPrefix.TOKEN_USER_S.key());
            String key=dto.getMobile() + "-" + dto.getSubject();
            token = tokens.get(key);
            if (token != null) {
                return token;
            } else {
                token = AppTokenUtil.ecode(dto);
                tokens.put(key, AppTokenUtil.ecode(dto));
            }
        }
        if (dto.getAppUserType().equals(AppUserType.USER_VISITOR)) {
            RMap<String, String> tokens = redissonClient.getMap(RedisPrefix.TOKEN_USER_VISITOR_S.key());
            String key=dto.getSubject();
            token = tokens.get(key);
            if (token != null) {
                return token;
            }else {
                token = AppTokenUtil.ecode(dto);
                tokens.put(key, AppTokenUtil.ecode(dto));
            }
        }
        return token;
    }

    @Override
    public void sendLoginVerifyCode(String phone) {
        SmsVerifyCodeDto smsBaseDto = new SmsVerifyCodeDto();
        smsBaseDto.setSmsMsg(RandomUtil.fourVerifyCode());
        smsBaseDto.setMobile(phone);
        smsSendService.sendVerifyCode(RedisPrefix.LOGIN_VERIFY_CODE, smsBaseDto);
    }

    @Override
    public void login(LoginQuery query) {
        //验证码验证
        if (!smsSendService.validVerifyCode(RedisPrefix.LOGIN_VERIFY_CODE, query.getMobile(), query.getVerifyCode())) {
            throw new DBException(ResponseCode.C_520002);
        }
        AppUser user = userMapper.getUserByCellPhone(query.getMobile());
        //用户不存在
        if(user==null){
            throw new DBException(ResponseCode.C_520003);
        }
        //用户被锁后无法进行登陆
        if (user.getLocked() == 1) {
            throw new DBException(ResponseCode.C_520001);
        }
    }

    @Override
    public void loginOut(UserDto userDto) {

    }
}
