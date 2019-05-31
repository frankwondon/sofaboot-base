package com.api.back.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.service.CmsNewsService;
import com.module.common.Response;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(description = "资讯管理")
@RestController
@RequestMapping("/cmsadmin/news")
public class CmsNewsController {
    @SofaReference
    private CmsNewsService cmsNewsService;


    @ApiOperation("查询咨询列表")
    @GetMapping("/list")
    public Response<IPage<CmsNews>> list(PageQuery pageQuery){
        return Response.success( cmsNewsService.list(pageQuery));
    }
    @ApiOperation(value = "新增或修改咨询",notes = "有ID就是修改")
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(CmsNews news,@ApiIgnore CurrentUser currentUser){
        cmsNewsService.addOrUpdate(news,currentUser);
        return Response.success(true);
    }

    @ApiOperation("删除咨询")
    @PostMapping("del")
    public Response del(Integer id){
        cmsNewsService.del(id);
        return Response.success(true);
    }
    @ApiOperation("禁用咨询")
    @PostMapping("disable")
    public Response disable(Integer id, @ApiIgnore CurrentUser currentUser){
        cmsNewsService.disable(id,currentUser);
        return Response.success(true);
    }

}
