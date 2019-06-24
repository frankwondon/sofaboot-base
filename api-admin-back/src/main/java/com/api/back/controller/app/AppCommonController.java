package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.app.service.AppProductService;
import com.module.admin.app.service.AppProductTypeService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdong
 * @date: 2019/6/21 10:07
 */
@Api(tags = "APP后台公共接口")
@RestController
@RequestMapping("/appadmin/common/common")
public class AppCommonController {
    @SofaReference
    private AppProductService aProductService;
    @SofaReference
    private AppProductTypeService aProductTypeService;

    @ApiOperation("模糊查询商品结果")
    @GetMapping("likeSearchProduct")
    public Response likeSearchProduct(String keyWord){
        return  Response.success(aProductService.likeSearchProduct(keyWord));
    }


    @ApiOperation("获取商品类型下拉")
    @GetMapping("productTypeSelect")
    public Response productTypeSelect(){
        return  Response.success(aProductTypeService.listUse());
    }
}
