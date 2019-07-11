package com.api.back.configure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.module.common.constant.DateFormatConstant;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;
import java.math.BigDecimal;
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

    /**
     * 解析钱的
     */
    public static class decimalJsonSerializer extends JsonSerializer<BigDecimal> {
        @Override
        public void serialize(BigDecimal date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(""+ date.divide(BigDecimal.valueOf(100),2));
        }
    }


    /**
     * 解析钱的
     */
    public static class decimalJsonDeserializer extends JsonDeserializer<BigDecimal> {
        @Override
        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new BigDecimal(jsonParser.getText()).multiply(BigDecimal.valueOf(100));
        }
    }
}
