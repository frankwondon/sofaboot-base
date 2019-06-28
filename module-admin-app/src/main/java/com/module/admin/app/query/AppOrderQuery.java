package com.module.admin.app.query;


import com.module.common.bean.PageQuery;
import jdk.net.SocketFlow;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppOrderQuery extends PageQuery{

    /**
     * 0全部订单1待付款订单2待发货3已发货4已完成5已关闭
     */
    private Integer status;
    /**
     * 当前页，每页条数
     * @param page
     * @param limit
     */
    public AppOrderQuery(int page, int limit) {
        super(page, limit);
    }

    /**起止时间限制条件*/

    //todo   交互时间类型

    private String startTime;
    private String endTime;

}
