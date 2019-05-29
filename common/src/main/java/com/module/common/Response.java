package com.module.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于封装返回web的结果
 *
 * @author wongdong
 * @date 2019/4/30
 */
@Data
public class Response<T> implements Serializable {
    private Response() {
    }

    private int code = ResponseCode.C_200.code();
    private String msg = ResponseCode.C_200.cnMsg();
    private T result = null;


    static public <T> Response success(T result) {
        Response<T> response = new Response<>();
        response.result = result;
        return response;
    }

    static public <T>Response fail(ResponseCode code, T result) {
        if (code == null) {
            throw new ResponseException("response code null");
        }
        Response<T> response = new Response<>();
        response.code = code.code();
        response.msg = code.cnMsg();
        response.result = result;
        return response;
    }

    static public<T> Response fail(ResponseCode code) {
        if (code == null) {
            throw new ResponseException("response code null");
        }
        Response<T> response = new Response<>();
        response.code = code.code();
        response.msg = code.cnMsg();
        return response;
    }

    static public <T> Response fail(ResponseCode code, String msg) {
        if (code == null) {
            throw new ResponseException("response code null");
        }
        Response<T> response = new Response<>();
        response.code = code.code();
        response.msg = msg;
        return response;
    }
}
