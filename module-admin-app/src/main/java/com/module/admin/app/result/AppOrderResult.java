package com.module.admin.app.result;

import com.module.admin.app.entity.AppOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/6/24 16:43
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class AppOrderResult extends AppOrder implements Serializable {


}
