package com.module.common;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于封装返回web的结果
 * @author wongdong
 * @date 2019/4/30
 */
@Data
public class Response<T> implements Serializable {
    private Response(){}
    private int code=ResponseCode.C_200.code();
    private String msg=ResponseCode.C_200.cnMsg();
    private T result=null;



    public static class Builder{
        public static <T> Response success(T result){
            Response<T> response=new Response<>();
            response.result=result;
            return response;
        }

        public static <T> Response fail(ResponseCode code,T result){
            if (code==null){
                throw new ResponseException("response code null");
            }
            Response<T> response=new Response<>();
            response.code=code.code();
            response.msg=code.cnMsg();
            response.result=result;
            return response;
        }

        public static <T> Response fail(ResponseCode code){
            if (code==null){
                throw new ResponseException("response code null");
            }
            Response<T> response=new Response<>();
            response.code=code.code();
            response.msg=code.cnMsg();
            return response;
        }
    }
}
