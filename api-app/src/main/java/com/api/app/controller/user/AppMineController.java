package com.api.app.controller.user;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.result.UserManagerResult;
import com.module.api.app.service.AppMineService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName AppMineController
 * @Description 我的页面
 * @Author YJT
 * @Date 2019/7/11 0011 9:37
 * @Version 1.0
 **/
@RequestMapping("appMine")
@RestController
@Api(tags = "我的界面")
public class AppMineController {


    @SofaReference
    private AppMineService appMineService;
    @PostMapping("sendAdvice")
    @ApiOperation(value = "反馈意见建议")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type"  ,paramType = "query",required = true,value = "0功能BUG，1优化体验，2其他"),
            @ApiImplicitParam(name = "text"  ,paramType = "query",required = true,value = "意见文本"),
            @ApiImplicitParam(name = "cellPhone" ,paramType = "query" ,required = true,value = "联系方式")
    })
    public Response sendAdvice(@RequestBody SendAdviceQuery query){
        return Response.success(appMineService.sendAdvice(query));
    }
}
