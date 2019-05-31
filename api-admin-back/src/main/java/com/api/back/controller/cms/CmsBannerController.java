package com.api.back.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.service.CmsBannerService;
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

@Api(description = "Banner管理")
@RestController
@RequestMapping("/cmsadmin/banner")
public class CmsBannerController {
    @SofaReference
    private CmsBannerService cmsBannerService;


    @ApiOperation("查询Banner列表")
    @GetMapping("/list")
    public Response<IPage<CmsBanner>> list(PageQuery pageQuery){
        return Response.success( cmsBannerService.list(pageQuery));
    }
    @ApiOperation(value = "新增或修改Banner",notes = "有ID就是修改")
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(CmsBanner cmsBanner,@ApiIgnore CurrentUser currentUser){
        cmsBannerService.addOrUpdate(cmsBanner,currentUser);
        return Response.success(true);
    }

    @ApiOperation("删除Banner")
    @PostMapping("del")
    public Response del(Integer id){
        cmsBannerService.del(id);
        return Response.success(true);
    }
    @ApiOperation("禁用Banner")
    @PostMapping("disable")
    public Response disable(Integer id, @ApiIgnore CurrentUser currentUser){
        cmsBannerService.disable(id,currentUser);
        return Response.success(true);
    }
}
