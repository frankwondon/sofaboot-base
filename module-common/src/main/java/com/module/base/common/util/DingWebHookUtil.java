package com.module.base.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangdong
 * @date: 2019/6/27 16:16
 */
@Slf4j
public class DingWebHookUtil {
    private static  final String url="https://oapi.dingtalk.com/robot/send?access_token=54b659677564d6523be12b756e32dd98da75cbb7dd29fcf187bfcc66ad929ff7";

    public static void sendCode(String phone,String code){
        JSONObject body=new JSONObject();
        body.put("msgtype","text");
        JSONObject text=new JSONObject();
        String content= StrUtil.format("验证码推送\n【手机号】{},【验证码】{},一天只能调用10次发送验证码,请节约使用。",phone,code);
        text.put("content",content);
        body.put("text",text);
        HttpResponse execute = HttpRequest.post(url).header("Content-Type", "application/json;charset=utf-8").body(body.toJSONString()).execute();
        log.info("发送钉钉通知结果{}",execute.body());
    }

}
