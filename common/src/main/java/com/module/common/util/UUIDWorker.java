package com.module.common.util;

import java.util.UUID;

/**
 * uuid生成器
 * @author wongdong
 * @date 2019/5/6
 */
public class UUIDWorker {
    /**
    * 利用java生成uuid
    */
    public static  String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
