package com.module.common.bean;


import lombok.Data;

@Data
public class PageQuery {
    private Integer limit=10;
    private Integer page=1;
}
