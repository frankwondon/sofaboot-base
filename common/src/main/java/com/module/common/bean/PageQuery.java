package com.module.common.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页")
public class PageQuery {
    @ApiModelProperty(value = "每页条数",example = "10")
    private Integer limit = 10;
    @ApiModelProperty(value = "当前页自1开始",example = "1")
    private Integer page = 1;
    @ApiModelProperty("模糊查询")
    private String keyWord;

    public PageQuery(int page, int limit) {
        this.limit = limit;
        this.page = page;
    }
}
