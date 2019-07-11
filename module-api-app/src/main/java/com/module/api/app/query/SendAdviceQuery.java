package com.module.api.app.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SendAdviceQuery
 * @Description 意见建议的实体
 * @Author YJT
 * @Date 2019/7/11 0011 15:17
 * @Version 1.0
 **/
@Data
public class SendAdviceQuery  implements Serializable{

    /**
     * 文本
     */
    private String text;
    /**
     * 电话
     */
    private String cellPhone;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 状态
     */
    private Integer status;

}
