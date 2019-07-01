package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppOrder;
import com.module.admin.app.result.AppOrderResult;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yjt
 * @since 2019-06-27
 */
public interface AppOrderMapper extends BaseMapper<AppOrder>{

    /**
     *
     * @param status        订单状态码
     * @param KeyWord       收件人模糊查询
     * @param startTime     时间查询起始时间
     * @param endTime       时间查询结束时间
     * @return
     */
    IPage<AppOrderResult> orderListByName(Page page, @Param("status") Integer status, @Param("KeyWord") String KeyWord,
                                    @Param("startTime")String startTime, @Param("endTime") String endTime);

    /**
     *
     * @param page
     * @param status        订单状态码
     * @param startTime     起始时间
     * @param endTime       结束时间
     * @return
     */
    IPage<AppOrderResult> orderList(Page page, @Param("status") Integer status,
                                    @Param("startTime")String startTime, @Param("endTime") String endTime);


    /**
     *
     * @param page
     * @param status        订单状态码
     * @param KeyWord       使用手机号查询
     * @param startTime     起始时间
     * @param endTime       结束时间
     * @return
     */
    IPage<AppOrderResult> orderListByPhone(Page page, @Param("status") Integer status, @Param("KeyWord") String KeyWord,
                                          @Param("startTime")String startTime, @Param("endTime") String endTime);
    /**
     *
     * @param orderId       使用订单号查询
     * @return
     */
    IPage<AppOrderResult> orderListByOrderId(@Param("orderId") String orderId);

}
