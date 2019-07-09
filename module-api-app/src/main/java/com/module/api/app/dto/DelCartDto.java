package com.module.api.app.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DelCartDto
 * @Description 购物车编辑选项 批量删除
 * @Author YJT
 * @Date 2019/7/9 0009 11:57
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class DelCartDto implements Serializable {

    private Integer userId;
    private List<Integer> skuIds;
}
