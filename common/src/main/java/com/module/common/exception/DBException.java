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

    public DBException(ResponseCode respontseCode) {
        super.setResponseCode(respontseCode);
    }


    public static void main(String[] args) {
        LimitException limitException = new LimitException(ResponseCode.C_500001);
        System.out.println(limitException.getResponseCode());
    }
}
