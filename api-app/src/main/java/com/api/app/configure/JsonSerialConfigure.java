package com.api.app.configure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.module.common.constant.DateFormatConstant;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义WebJSON序列化
 *@author wangdong
 *@date 2019/6/25
 */
@JsonComponent
public class JsonSerialConfigure {

    private static  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatConstant.DATE_TIME);

    /**
     * 日期格式化
     */
    public static class DateJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(formatter.format(date));
        }
    }

    /**
     * 解析日期字符串
     */
    public static class DateJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            LocalDateTime date=LocalDateTime.parse(jsonParser.getText(),formatter);
            return date;
        }
    }


}
