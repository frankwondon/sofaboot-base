package com.module.common.exception;

import com.module.common.ResponseCode;

/**
 * 验证属性异常
 * @author wangdong
 * @date: 2019/6/28 14:06
 */
public class ValidationException extends BaseException{
    public ValidationException(ResponseCode responseCode) {
        super(responseCode);
    }

    public ValidationException(ResponseCode responseCode, String msg) {
        super(responseCode, msg);
    }

}
