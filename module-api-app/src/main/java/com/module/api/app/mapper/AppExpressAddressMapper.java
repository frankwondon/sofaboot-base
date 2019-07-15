package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.api.app.dto.AddressDto;
import com.module.api.app.entity.AppExpressAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收货地址管理 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-07-02
 */
public interface AppExpressAddressMapper extends BaseMapper<AppExpressAddress> {
    /**
     * 查询用户下的收获地址
     * @param userId
     * @return
     */
    List<AddressDto> listAddress(Integer userId);

    /**
     * 获取默认收货地址
     * @param userId
     * @return
     */
    AddressDto findDefaultAddress(Integer userId);

    /**
     * 查询指定收货地址
     * @param userId
     * @param addrId
     * @return
     */
    AddressDto findPointAddress(@Param("userId") Integer userId,@Param("addrId") Integer addrId);
    /**
     * 逻辑删除
     * @param userId 用户ID
     * @param addrId 地址ID
     * @return
     */
    int logicDel(@Param("userId") Integer userId,@Param("addrId") Integer addrId);

    /**
     * 设置默认收货地址
     * @param userId
     * @param addrId
     * @return
     */
    int defaultAddress(@Param("userId") Integer userId,@Param("addrId") Integer addrId);


    /**
     * 设置除默认地址外的其他地址为常规地址
     * @param userId
     * @param addrId
     * @return
     */
    int restoreDefaultAddress(@Param("userId") Integer userId,@Param("addrId") Integer addrId);

}
