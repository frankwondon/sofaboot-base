package com.api.maintest;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.module.common.constant.RegexPattern;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.swagger.schema.ApiModelTypeNameProvider;

import java.io.File;
import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(ReUtil.isMatch(RegexPattern.MOBILE,"15001200836"));
    }
}
