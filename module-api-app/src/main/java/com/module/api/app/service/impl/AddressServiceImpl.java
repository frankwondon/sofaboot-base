package com.module.api.app.service.impl;

import com.module.api.app.dto.AddressDto;
import com.module.api.app.entity.AppExpressAddress;
import com.module.api.app.mapper.AppExpressAddressMapper;
import com.module.api.app.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/7/2 10:30
 */
public class AddressServiceImpl implements AddressService {

    @Resource
    private AppExpressAddressMapper expressAddressMapper;

    @Override
    public void addOrUpdate(AddressDto address, Integer userId) {
        AppExpressAddress addressEntity=new AppExpressAddress();
        addressEntity.setUserId(userId);
        BeanUtils.copyProperties(address,addressEntity);
        if (address.getAddressId()!=null){
            expressAddressMapper.updateById(addressEntity);
        }else {
            address.getAddressId();
            expressAddressMapper.insert(addressEntity);
        }
    }

    @Override
    public void del(Integer addrId,Integer userId) {
        expressAddressMapper.logicDel(userId,addrId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void defaultAddress(Integer id,Integer userId) {
        expressAddressMapper.defaultAddress(userId,id);
        expressAddressMapper.restoreDefaultAddress(userId,id);
    }

    @Override
    public List<AddressDto> addressList(Integer userId) {
        return expressAddressMapper.listAddress(userId);
    }
}
