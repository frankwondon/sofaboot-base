package com.module.common.json;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * @author wangdong
 * @date: 2019/7/2 16:36
 */
public class GlobalKeyDeserializer extends KeyDeserializer {
    private static final String ID_FILED="id";
    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) throws IOException {
        if (s.equals(ID_FILED)){
            return "_id";
        }else {
            return s;
        }
    }
}
