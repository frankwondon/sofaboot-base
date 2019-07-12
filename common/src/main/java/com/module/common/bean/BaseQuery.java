package com.module.common.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/11 10:41
 */
@Data
@ApiModel("请求包装")
public class BaseQuery<T> implements Serializable {
    @Valid
    private T data;
}
