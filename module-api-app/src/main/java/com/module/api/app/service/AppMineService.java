package com.module.api.app.service;

import com.module.api.app.query.SendAdviceQuery;
import com.module.common.Response;

/**
 * @ClassName AppMineService
 * @Description 我的界面 接口
 * @Author YJT
 * @Date 2019/7/10 0010 9:03
 * @Version 1.0
 **/
public interface AppMineService {

   boolean sendAdvice(SendAdviceQuery query);
}
