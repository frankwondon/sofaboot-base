package com.module.common.exception;

import com.module.common.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 数据库增删改查的异常
 * @author wangdong
 * @date: 2019/6/27 9:15
 */
public class DBException extends BaseException {
    private ResponseCode responseCode;


    public DBException(ResponseCode respontseCode) {
        super(respontseCode);
    }
}
