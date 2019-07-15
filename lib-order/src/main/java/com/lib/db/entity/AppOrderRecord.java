package com.lib.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2019-07-15
 */
@Data
@Accessors(chain = true)
public class AppOrderRecord {

private static final long serialVersionUID=1L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作类型0取消订单
     */
    private Integer operationType;

    /**
     * 操作人类型0系统1自己
     */
    private Integer operationUserType;

    /**
     * 操作记录描述
     */
    private String operationRemark;


}
