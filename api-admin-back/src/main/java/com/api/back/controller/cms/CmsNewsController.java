package com.api.back.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.service.CmsNewsService;
import com.module.common.Response;
import com.module.common.bean.AdminCurrentUser;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "资讯管理")
@RestController
@RequestMapping("/cmsadmin/news/index")
public class CmsNewsController {
    @SofaReference
    private CmsNewsService cmsNewsService;


    @ApiOperation("查询咨询列表")
    @GetMapping("/list")
    public Response<IPage<CmsNews>> list(PageQuery pageQuery){
        return Response.success( cmsNewsService.list(pageQuery));
    }

    @ApiOperation(value = "新增或修改咨询",notes = "有ID就是修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,paramType = "query",value = "有ID就是修改"),
            @ApiImplicitParam(name = "name",paramType = "query" ,required = true,value = "产品名称"),
            @ApiImplicitParam(name = "imgUrl" ,paramType = "query",required = true,value = "图片地址"),
            @ApiImplicitParam(name = "descText",paramType = "query" ,required = true,value = "详细描述"),
            @ApiImplicitParam(name = "sort" ,paramType = "query",required = true,value = "顺序"),
            @ApiImplicitParam(name = "locked",paramType = "query" ,value = "0启用,1禁用 默认禁用"),
    })
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(@ApiIgnore CmsNews news,@ApiIgnore AdminCurrentUser currentUser){
        cmsNewsService.addOrUpdate(news,currentUser);
        return Response.success(true);
    }

    @ApiOperation("删除咨询")
    @PostMapping("del")
    public Response del(Integer id){
        cmsNewsService.del(id);
        return Response.success(true);
    }
    @ApiOperation("启用/禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = true,value = "要操作的ID"),
            @ApiImplicitParam(name = "disable"  ,paramType = "query",required = true,value = "true启用false禁用"),
    })
    @PostMapping("disable")
    public Response disable(Integer id, Boolean disable,@ApiIgnore AdminCurrentUser currentUser){
        cmsNewsService.disable(id,disable,currentUser);
        return Response.success(true);
    }

}
