package com.module.api.app.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangdong
 * @date: 2019/6/24 10:32
 */
@ApiModel("登陆实体")
@Data
public class LoginQuery {
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("验证码")
    private String verifyCode;
    @ApiModelProperty("设备ID")
    private String deviceId;
}
