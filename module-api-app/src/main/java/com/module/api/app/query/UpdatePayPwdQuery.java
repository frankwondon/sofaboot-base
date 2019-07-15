package com.module.api.app.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CheckCodeQuery
 * @Description 更改支付密码实体
 * @Author YJT
 * @Date 2019/7/10 0010 17:54
 * @Version 1.0
 **/
@ApiModel("更改支付密码实体")
@Data
public class UpdatePayPwdQuery implements Serializable {

    @ApiModelProperty("验证码")
    private String verifyCode;

    @ApiModelProperty("支付密码")
    private String payWord;
    /**
     * 用户手机号
     */
    private String cellPhoneNum;

}
