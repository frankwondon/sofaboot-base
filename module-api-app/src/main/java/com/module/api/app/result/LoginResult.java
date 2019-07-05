package com.module.api.app.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/6/28 11:23
 */
@Data
@Builder
@ApiModel("登陆返回")
public class LoginResult implements Serializable {

    private Integer userId;
    @ApiModelProperty("手机号")
    private String cellPhoneNum;
    @ApiModelProperty("头像")
    private String headImg;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("x-token")
    private String token;

}
