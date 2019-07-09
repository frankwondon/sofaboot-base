package com.module.api.app.service.impl;

import cn.hutool.core.util.ReUtil;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.dto.UserDto;
import com.module.api.app.entity.AppUser;
import com.module.api.app.mapper.UserMapper;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.result.LoginResult;
import com.module.api.app.service.UserService;
import com.module.base.common.constant.RedisPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;
import com.module.base.common.service.SMSSendService;
import com.module.common.ResponseCode;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;
import com.module.common.constant.RegexPattern;
import com.module.common.exception.DBException;
import com.module.common.exception.ValidationException;
import com.module.common.util.AppTokenUtil;
import com.module.common.util.RandomUtil;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdong
 * @date: 2019/6/25 14:26
 */
public class UserServiceImpl implements UserService {
    @Value("${unit.token.user_expired_days}")
    private Integer userExpiredDays;

    @Value("${unit.token.visitor_expired_days}")
    private Integer visitorExpiredDays;
    @SofaReference
    private SMSSendService smsSendService;
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public String getToken(AppTokenDto dto) {
        String token = null;
        if (dto.getAppUserType().equals(AppUserType.USER)) {
            //用户token用手机号+设备ID组合
            RMapCache<String, String> tokens = redissonClient.getMapCache(RedisPrefix.TOKEN_USER_S.key());
            RMapCache<String, AppCurrentUser> users = redissonClient.getMapCache(RedisPrefix.SESSION_USER_S.key());
            String key = dto.getMobile() + "-" + dto.getSubject();
            token = tokens.get(key);
            if (token != null) {
                return token;
            } else {
                token = AppTokenUtil.ecode(dto);
                tokens.put(key, token, userExpiredDays, TimeUnit.DAYS);
                //用户对象
                users.put(key,dto.getSessionUser(),userExpiredDays, TimeUnit.DAYS);
            }
        }
        if (dto.getAppUserType().equals(AppUserType.USER_VISITOR)) {
            RMapCache<String, String> tokens = redissonClient.getMapCache(RedisPrefix.TOKEN_USER_VISITOR_S.key());
            String key = dto.getSubject();
            token = tokens.get(key);
            if (token != null) {
                return token;
            } else {
                token = AppTokenUtil.ecode(dto);
                tokens.put(key, token, visitorExpiredDays, TimeUnit.DAYS);
            }
        }
        return token;
    }

    @Override
    public AppCurrentUser getTokenUser(AppTokenDto dto) {
        RMapCache<String, AppCurrentUser> users = redissonClient.getMapCache(RedisPrefix.SESSION_USER_S.key());
        String key = dto.getMobile() + "-" + dto.getSubject();
        return users.get(key);
    }

    @Override
    public void sendLoginVerifyCode(String phone) {
        if (!ReUtil.isMatch(RegexPattern.MOBILE, phone)) {
            throw new ValidationException(ResponseCode.C_500003, "手机号格式错误");
        }
        SmsVerifyCodeDto smsBaseDto = new SmsVerifyCodeDto();
        smsBaseDto.setSmsMsg(RandomUtil.fourVerifyCode());
        smsBaseDto.setMobile(phone);
        smsSendService.sendVerifyCode(RedisPrefix.LOGIN_VERIFY_CODE, smsBaseDto);
    }

    @Override
    public LoginResult login(LoginQuery query) {
        //验证码验证
        if (!smsSendService.validVerifyCode(RedisPrefix.LOGIN_VERIFY_CODE, query.getMobile(), query.getVerifyCode())) {
            throw new DBException(ResponseCode.C_520002);
        }
        AppUser user = userMapper.getUserByCellPhone(query.getMobile());
        //用户不存在 创建一个账号
        LoginResult result = null;
        if (user == null) {
            user = createUser(query.getMobile());
        } else {
            //用户被锁后无法进行登陆
            if (user.getLocked() == 1) {
                throw new DBException(ResponseCode.C_520001);
            }
        }
        //查询购物车数量（总数）

        result = LoginResult.builder().userId(user.getId()).cellPhoneNum(user.getCellPhoneNum()).headImg("").username(user.getCellPhoneNum()).build();
        return result;
    }

    @Override
    public void loginOut(UserDto userDto) {

    }

    private AppUser createUser(String phone) {
        AppUser user = new AppUser();
        user.setCellPhoneNum(phone);
        user.setUsername(phone);
        user.setHeadImg("");
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }


}
