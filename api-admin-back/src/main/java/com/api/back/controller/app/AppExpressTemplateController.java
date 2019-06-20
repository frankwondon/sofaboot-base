package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.app.dto.ExpressTemplateDto;
import com.module.admin.app.entity.AppExpressTemplateArea;
import com.module.admin.app.service.AppExpressTemplateService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "快递模板")
@RestController
@RequestMapping("/appadmin/product/expressTemplate")
public class AppExpressTemplateController {
    @SofaReference
    private AppExpressTemplateService expressTemplateService;

    @GetMapping("list")
    @ApiOperation(value = "模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", paramType = "query", required = true, value = "模糊查询条件"),
    })
    public Response<ExpressTemplateDto> productList(String keyWord) {
        return Response.success(expressTemplateService.listTemplate());
    }


    @PostMapping("add")
    @ApiOperation(value = "添加运费模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", required = true, value = "模板名字"),
            @ApiImplicitParam(name = "priceRule", paramType = "query", required = true, value = "0按件计费1按KG计费"),
            @ApiImplicitParam(name = "type", paramType = "query", required = true, value = "0卖家承担运费1买家承担运费"),
            @ApiImplicitParam(name = "defaultArea", paramType = "query", required = true, value = "采用非默认运费，1采用默认运费"),
            @ApiImplicitParam(name = "area", paramType = "query", required = true, value = "地区配置LIST结构", dataTypeClass = AppExpressTemplateArea.class),
    })
    public Response addTemplate(ExpressTemplateDto dto) {
        expressTemplateService.createTemplate(dto);
        return Response.success(true);
    }

    @PostMapping("update")
    @ApiOperation(value = "修改运费模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "query", required = true, value = "模板ID"),
            @ApiImplicitParam(name = "name", paramType = "query", required = true, value = "模板名字"),
            @ApiImplicitParam(name = "priceRule", paramType = "query", required = true, value = "0按件计费1按KG计费"),
            @ApiImplicitParam(name = "type", paramType = "query", required = true, value = "0卖家承担运费1买家承担运费"),
            @ApiImplicitParam(name = "defaultArea", paramType = "query", required = true, value = "采用非默认运费，1采用默认运费"),
            @ApiImplicitParam(name = "area", paramType = "query", required = true, value = "地区配置LIST结构", dataTypeClass = AppExpressTemplateArea.class),
    })
    public Response updateTemplate(ExpressTemplateDto dto) {
        expressTemplateService.updateTemplate(dto);
        return Response.success(true);
    }


    @PostMapping("del")
    @ApiOperation(value = "删除运费模板")
    public Response delTemplate(Integer tempId) {
        expressTemplateService.delTemplate(tempId);
        return Response.success(true);
    }
}
