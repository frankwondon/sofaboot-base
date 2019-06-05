package com.module.admin.back.query;

import com.module.common.bean.PageQuery;

import java.io.Serializable;
/**
 *
 *@author wangdong
 *@date 2019/5/20
 */
public class BackUserQuery extends PageQuery implements Serializable {
    public BackUserQuery(int page, int limit) {
        super(page, limit);
    }
}
