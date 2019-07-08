package com.api.app.controller.order;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.intercept.AuthLogin;
import com.module.api.app.dto.AddressDto;
import com.module.api.app.service.AddressService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wangdong
 * @date: 2019/7/2 9:13
 */
@RestController
@RequestMapping("address")
@Api(tags = "收获地址")
@AuthLogin
public class AddressController {
    @SofaReference
    private AddressService addressService;

    @ApiOperation("收获地址列表")
    @GetMapping("addressList")
    public Response addressList(@ApiIgnore AppCurrentUser user){
        return Response.success(addressService.addressList(user.getId()));
    }


    @ApiOperation("添加地址列表")
    @PostMapping("add")
    public Response add(@Validated @RequestBody AddressDto addressDto,@ApiIgnore AppCurrentUser user){
        addressService.addOrUpdate(addressDto,user.getId());
        return Response.success(true);
    }

    @ApiOperation("添加地址列表")
    @PostMapping("update")
    public Response update(@Validated @RequestBody AddressDto addressDto,@ApiIgnore AppCurrentUser user){
        addressService.addOrUpdate(addressDto,user.getId());
        return Response.success(true);
    }


    @ApiOperation("设为默认地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId",required = true,value = "地址的ID")
    })
    @GetMapping("defaultAddress")
    public Response defaultAddress(Integer addressId,@ApiIgnore AppCurrentUser user){
        addressService.defaultAddress(addressId,user.getId());
        return Response.success(true);
    }

    @ApiOperation("删除地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId",required = true,value = "地址的ID")
    })
    @GetMapping("delAddress")
    public Response delAddress(Integer addressId,@ApiIgnore AppCurrentUser user){
        addressService.del(addressId,user.getId());
        return Response.success(true);
    }
}
