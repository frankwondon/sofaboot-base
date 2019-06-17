package com.api.cms.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.service.CmsNewsService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "咨询页")
@RestController
@RequestMapping("/cms/news")
public class CmsNewsController {
    @SofaReference
    private CmsNewsService newsService;
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit",paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "page",paramType = "query" ,required = true,value = "当前页数"),
    })
    @GetMapping("/newsList")
    public Response<IPage<CmsNews>> newsList(PageQuery query) {
        return Response.success(newsService.showList(query));
    }


    @ApiOperation(value = "获取新闻详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",paramType = "query" ,required = true,value = "ID"),
    })
    @GetMapping("/newsDetail")
    public Response<CmsNews> newsDetail(Integer id) {
        return Response.success(newsService.findById(id));
    }
}
