package com.module.admin.app.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
@Data
@Accessors(chain = true)
public class BaseArea {

private static final long serialVersionUID=1L;

    private Integer code;

    private String name;

    private Integer parentCode;

    /**
     * 深度
     */
    private Integer depth;


}
