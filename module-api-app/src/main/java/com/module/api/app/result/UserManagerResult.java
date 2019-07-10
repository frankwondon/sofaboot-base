package com.module.api.app.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/6/21 15:30
 */
@Data
public class UserManagerResult implements Serializable {

    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty
    private String  payWord;
    @ApiModelProperty("支付密码状态")
    private Integer  payStatus;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty(value = "性别",notes = "0男1女")
    private Integer sex;

    @ApiModelProperty("用户电话")
    private String phoneNum;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("银行卡号")
    private String bankId;

    @ApiModelProperty(value = "银行卡确认状态",notes = "0未确认1已确认")
    private Integer bankConfirm;
    @ApiModelProperty(value = "wx账号",notes = "0未绑定 其他绑定的wx账号")
    private Integer appWx;
    @ApiModelProperty(value = "qq账号",notes = "0未绑定 其他绑定的qq账号")
    private Integer appQq;


    @ApiModelProperty("头像")
    private String headImg;

    @ApiModelProperty("待付款")
    private Integer dfk;
    @ApiModelProperty("待发货")
    private Integer dfh;
    @ApiModelProperty("待收货")
    private Integer dsh;
    @ApiModelProperty("已完成")
    private Integer ywc;
    @ApiModelProperty("全部")
    private Integer total;





}
