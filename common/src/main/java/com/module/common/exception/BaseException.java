package com.module.common.exception;

import com.module.common.ResponseCode;

/**
 * 基础异常类
 * @author wangdong
 * @date: 2019/6/27 9:36
 */
public class BaseException extends RuntimeException {


    private ResponseCode responseCode;

    BaseException (){
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
