package com.module.common;


public enum  ResponseCode {
    C_200(200,"请求成功","request success");
    private int code;
    private String cnMsg;
    private String enMsg;
    private  ResponseCode(int code,String cnMsg,String enMsg){
        this.code=code;
        this.cnMsg=cnMsg;
    }
}
