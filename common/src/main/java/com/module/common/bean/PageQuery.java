package com.module.common.bean;


import lombok.Data;

@Data
public class PageQuery {
    private Integer limit = 10;
    private Integer page = 1;

    public PageQuery(int page, int limit) {
        this.limit = limit;
        this.page = page;
    }
}
