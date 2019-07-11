package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.api.app.query.SendAdviceQuery;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName AppMineMapper
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/11 0011 15:27
 * @Version 1.0
 **/
public interface AppMineMapper extends BaseMapper<SendAdviceQuery>{
   int sendAdvice(@Param("advice") SendAdviceQuery query);
}
