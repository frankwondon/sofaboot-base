package com.module.base.common.constant;

/**
 * @author wangdong
 * @date: 2019/6/25 9:17
 */
public enum RedisPrefix {
    RATE_LIMIT("RATE_LIMIT:","流量限制"),
    LOGIN_VERIFY_CODE("LOGIN_VERIFY_CODE:","登陆验证码"),
    RPWD_CODE("RPWD_CODE:","修改密码验证码"),
    TOKEN_USER_S("TOKEN_USERS","用户token"),
    TOKEN_USER_VISITOR_S("TOKEN_USERS","游客token");
    private String key;
    private String desc;
    RedisPrefix(String key, String desc){
        this.key=key;
        this.desc=desc;
    }
    public String key(){
        return key;
    }
}
