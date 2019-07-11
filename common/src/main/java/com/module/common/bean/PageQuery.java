package com.module.common.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.swing.text.DefaultFormatter;
import java.io.Serializable;
/**
 * 分页统一接口
 *@author wangdong
 *@date 2019/7/11
 */
@Data
@ApiModel("分页")
public class PageQuery<T> implements Serializable {
    @ApiModelProperty(value = "每页条数",example = "10")
    private Integer limit = 10;
    @ApiModelProperty(value = "当前页自1开始",example = "1")
    private Integer page = 1;
    @ApiModelProperty("模糊查询")
    private String keyWord;
    @ApiModelProperty("需要包含的具体实体、集合")
    private T data;
    public PageQuery(int page, int limit) {
        this.limit = limit;
        this.page = page;
    }
}
