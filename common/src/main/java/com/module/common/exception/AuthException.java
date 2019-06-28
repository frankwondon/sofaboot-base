package com.module.common.exception;

import com.module.common.ResponseCode;

/**
 * 认证异常
 * @author wangdong
 * @date: 2019/6/28 13:28
 */
public class AuthException extends BaseException {

   public AuthException(ResponseCode responseCode) {
       super(responseCode);
   }

    public AuthException(ResponseCode responseCode, String msg) {
        super(responseCode, msg);
    }
}
