package com.module.api.app.mapper;

import com.module.api.app.entity.AppProductSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-14
 */
public interface AppProductSkuMapper extends BaseMapper<AppProductSku> {
        List<AppProductSku> listProductSku(Integer productId);
        void delProductSku(Integer productId);
}
