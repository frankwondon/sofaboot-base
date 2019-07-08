package com.module.api.app.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/2 11:16
 */

@Data
public class BaseDto implements Serializable {
    @ApiModelProperty(value = "id一般修改时用到",example = "1")
    private Integer id;
}
