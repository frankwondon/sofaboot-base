package com.module.base.common.configure;


import cn.hutool.core.util.StrUtil;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
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
@EnableConfigurationProperties({RedisConfigProperties.class})
@ConditionalOnProperty(prefix = "unit.redis", name = "enable", havingValue = "true")
public class RedisConfig {
    @Resource
    private RedisConfigProperties redisConfigProperties;

    private Config config() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(redisConfigProperties.getAddress())
                .setConnectionPoolSize(50);
        if (StrUtil.isNotBlank(redisConfigProperties.getPassWord())) {
            singleServerConfig.setPassword(redisConfigProperties.getPassWord());
        }
        if (redisConfigProperties.getDbNum() != null) {
            singleServerConfig.setDatabase(redisConfigProperties.getDbNum());
        } else {
            singleServerConfig.setDatabase(0);
        }
        if (redisConfigProperties.getTimeOut() != null) {
            singleServerConfig.setTimeout(redisConfigProperties.getTimeOut());
        } else {
            singleServerConfig.setTimeout(3000);
        }
        config.setThreads(20);
        return config;
    }


    @Bean
    @SofaService
    public RedissonClient redisClient() {
        return Redisson.create(config());
    }

}
