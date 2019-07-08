package com.module.api.app.configure;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * @author wangdong
 * @date: 2019/7/5 17:30
 */
public class GloblKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) throws IOException {
        if (s.equals("id")){
            return "_id";
        }else {
            return s;
        }
    }
}
