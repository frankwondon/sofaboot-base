package com.api.maintest;

import cn.hutool.core.util.StrUtil;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.swagger.schema.ApiModelTypeNameProvider;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class MainTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(LocalDateTime.now().format(formatter));
    }
}
