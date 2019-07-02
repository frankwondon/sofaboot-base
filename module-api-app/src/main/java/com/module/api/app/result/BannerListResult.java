package com.module.api.app.result;

import com.module.api.app.entity.AppBanner;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName BannerListResult
 * @Description 轮播图结果
 * @Author Administrator
 * @Date 2019/7/1 0001 14:51
 * @Version 1.0
 **/

@Data
@ApiModel("轮播图列表")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BannerListResult  extends AppBanner{
}
