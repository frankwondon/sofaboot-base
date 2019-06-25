package com.module.base.common.configure;


import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author wangdong
 * @date: 2019/6/25 18:45
 */
@Configuration
@EnableConfigurationProperties
@ConditionalOnProperty(prefix = "unit.redis",name = "enable",havingValue = "true")
public class RedisConfig {
  @Resource
  private RedisConfigProperties redisConfigProperties;
  private Config config(){
    Config config=new Config();
    config.useSingleServer().setAddress(redisConfigProperties.getAddress())
            .setDatabase(redisConfigProperties.getDbNum())
            .setConnectionPoolSize(20)
            .setConnectTimeout(redisConfigProperties.getTimeOut())
            .setPassword(redisConfigProperties.getPassWord());
    config.setThreads(20);
    return config;
  }

}
