package com.module.common;
/**
* 用于返回给web端的状态码
*@author wangdong
*@date 2019/4/30
*/
public enum  ResponseCode {
    C_200(200,"请求成功"),
    C_302(302,"认证失败"),
    C_500(500,"请求失败"),
    C_500001(500001,"已经存在的资源"),
    C_500002(500002,"禁止操作超管权限"),
    C_500003(500003,"参数错误,请检查"),
    C_500004(500004,"还有子集未删除,不能执行删除操作");

    private int code;
    private String cnMsg;
    private String enMsg;
    private  ResponseCode(int code,String cnMsg){
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
