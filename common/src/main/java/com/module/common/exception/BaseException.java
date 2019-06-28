package com.module.common.exception;

import com.alibaba.fastjson.JSON;
import com.module.common.ResponseCode;

/**
 * 基础异常类
 * @author wangdong
 * @date: 2019/6/27 9:36
 */
public class BaseException extends RuntimeException {

    /**
     * 状态码
     */
    private ResponseCode responseCode;
    /**
     * 自定义的返回消息
     */
    private String msg;

    BaseException (ResponseCode responseCode){
        super(JSON.toJSONString(responseCode));
        setResponseCode(responseCode);
    }

    BaseException (ResponseCode responseCode,String msg){
        super(msg);
        setResponseCode(responseCode);
        setMsg(msg);

    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
