package com.module.base.common.configure;


import org.redisson.config.Config;

/**
 * @author wangdong
 * @date: 2019/6/25 18:45
 */
public class RedisConfig {

  private Config config(){
    Config config=new Config();
    config.useClusterServers()
            // use "rediss://" for SSL connection
            .addNodeAddress("redis://127.0.0.1:7181");
    config.setThreads(20);
  }
}
