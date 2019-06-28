package com.module.base.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SmsVerifyCodeDto extends SmsBaseDto implements Serializable {
}
