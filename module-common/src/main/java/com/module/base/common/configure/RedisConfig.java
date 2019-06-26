package com.module.base.common.configure;


import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.FstCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
    SingleServerConfig singleServerConfig = config.useSingleServer()
            .setAddress(redisConfigProperties.getAddress())
            .setDatabase(redisConfigProperties.getDbNum())
            .setConnectionPoolSize(50)
            .setConnectTimeout(redisConfigProperties.getTimeOut());
    if (StrUtil.isNotBlank(redisConfigProperties.getPassWord())){
      singleServerConfig.setPassword(redisConfigProperties.getPassWord());
    }
    if (redisConfigProperties.getDbNum()!=null){
      singleServerConfig.setDatabase(redisConfigProperties.getDbNum());
    }else {
      singleServerConfig.setDatabase(0);
    }
    config.setThreads(20);
    return config;
  }


  @Bean
  public RedissonClient redisClient(){
    return Redisson.create(config());
  }

}
