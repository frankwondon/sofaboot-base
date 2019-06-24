package com.module.base.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsBaseDto implements Serializable {
    /**
     * 要发送的手机号
     */
    private String mobile;
    /**
     * 要发送的内容
     */
    private String smsMsg;
}
