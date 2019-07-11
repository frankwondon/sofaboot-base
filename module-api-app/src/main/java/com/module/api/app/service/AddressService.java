package com.module.api.app.service;

import com.module.api.app.dto.AddressDto;
import com.module.api.app.entity.AppExpressAddress;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/7/2 9:14
 */
public interface AddressService {

    /**
     * 新增或修改，最大10个收货地址
     * @param address
     * @param userId
     */
    void addOrUpdate(AddressDto address, Integer userId);

    /**
     * 删除地址
     * @param addrId
     * @param userId
     */
    void del(Integer addrId,Integer userId);

    /**
     * 设置为默认收获地址
     * @param id
     */
    void defaultAddress(Integer id,Integer userId);

    /**
     * 收获地址列表
     * @param userId
     * @return
     */
    List<AddressDto> addressList(Integer userId);

    /**
     * 获取用户默认收货地址
     * @return
     */
    AddressDto findDefaultAddress(Integer userId);
}
