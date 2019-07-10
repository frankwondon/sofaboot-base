package com.module.api.app.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CheckCodeQuery
 * @Description 验证码校验
 * @Author YJT
 * @Date 2019/7/10 0010 17:54
 * @Version 1.0
 **/
@ApiModel("支付密码实体")
@Data
public class CheckCodeQuery {
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("验证码")
    private String verifyCode;

}
