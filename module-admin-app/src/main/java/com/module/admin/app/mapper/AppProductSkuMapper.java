package com.module.admin.app.mapper;

import com.module.admin.app.entity.AppProductSku;
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
        /**
         * 查询商品下的SKU
         * @param productId
         * @return
         */
        List<AppProductSku> listProductSku(Integer productId);

        /**
         * 删除商品下的SKU
         * @param productId
         */
        void delProductSku(Integer productId);
}
