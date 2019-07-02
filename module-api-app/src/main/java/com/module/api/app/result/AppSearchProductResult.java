package com.module.api.app.result;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@ApiModel("商品查询列表")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppSearchProductResult  extends AppProductResult{


    private String name;
    private String link;
    private String markedPrice;

}
