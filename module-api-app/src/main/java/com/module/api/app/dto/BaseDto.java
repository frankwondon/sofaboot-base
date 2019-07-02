package com.module.api.app.dto;

import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.module.common.json.GlobalJsonNameingStrategy;
import com.module.common.json.GlobalKeyDeserializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/2 11:16
 */
@JsonDeserialize(keyUsing = GlobalKeyDeserializer.class)
@Data
public class BaseDto implements Serializable {
    @ApiModelProperty(value = "id一般修改时用到",example = "1")
    private Integer id;
}
