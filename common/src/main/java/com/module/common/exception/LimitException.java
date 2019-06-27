package com.module.common.exception;

import com.module.common.ResponseCode;

/**
 * 进行限制型验证的时候抛出的异常
 * @author wangdong
 * @date: 2019/6/27 9:15
 */
public class LimitException extends BaseException {

    LimitException(ResponseCode respontseCode) {
        super(respontseCode);
    }
}
