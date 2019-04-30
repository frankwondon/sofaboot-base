package com.module.common;


public enum  ResponseCode {
    C_200(200,"请求成功");
    private int code;
    private String cnMsg;
    private String enMsg;
    private  ResponseCode(int code,String cnMsg){
        this.code=code;
        this.cnMsg=cnMsg;
    }
}
