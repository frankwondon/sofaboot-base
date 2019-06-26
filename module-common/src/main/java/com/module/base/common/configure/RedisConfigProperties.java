package com.module.base.common.configure;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangdong
 * @date: 2019/6/25 18:45
 */
@ConfigurationProperties(prefix = "unit.redis")
@Data
public class RedisConfigProperties {
        private Boolean enable;
        private String address;
        private Integer dbNum;
        private String passWord;
        private Integer  timeOut;


}
