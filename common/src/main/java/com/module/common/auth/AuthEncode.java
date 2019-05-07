package com.module.common.auth;

import java.io.Serializable;

/**
 * JwtEncode之后的对象
 * @author wongdong
 * @date 2019/5/6
 */
public class AuthEncode implements Serializable {
    private String token;
    /*用于标记此token生成时的ID*/
    private String tokenId;
}
