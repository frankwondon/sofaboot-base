package com.module.common.bean;

import com.module.common.constant.AppUserType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/6/25 16:32
 */
@Data
@Builder
public class AppTokenDto implements Serializable {
    private String subject;
    private AppUserType appUserType;
    private String userAgent;
    private String mobile;

}
