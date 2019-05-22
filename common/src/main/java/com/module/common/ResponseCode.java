package com.module.common;
/**
* 用于返回给web端的状态码
*@author wangdong
*@date 2019/4/30
*/
public enum  ResponseCode {
    C_200(200,"请求成功","mylib success"),
    C_302(302,"认证失败","auth fail"),
    C_500(500,"请求失败","mylib fail");

    private int code;
    private String cnMsg;
    private String enMsg;
    private  ResponseCode(int code,String cnMsg,String enMsg){
        this.code=code;
        this.cnMsg=cnMsg;
    }
    public int code(){
        return code;
    }
    public String cnMsg(){
        return cnMsg;
    }
    public String enMsg(){
        return cnMsg;
    }
}
