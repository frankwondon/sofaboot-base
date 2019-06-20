package com.module.admin.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.module.admin.app.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
