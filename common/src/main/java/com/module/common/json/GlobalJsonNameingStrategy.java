package com.module.common.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author wangdong
 * @date: 2019/7/2 10:01
 */
public class GlobalJsonNameingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
    private static final String ID_FILED="id";
    @Override
    public String translate(String filedName) {
        if (filedName.equals(ID_FILED)){
            return "_id";
        }else {
            return filedName;
        }
    }
}
