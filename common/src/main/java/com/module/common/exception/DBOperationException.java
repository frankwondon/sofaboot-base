package com.module.common.exception;

import com.module.common.ResponseCode;
import lombok.Data;

@Data
public class DBOperationException extends RuntimeException {
    private int code;
    private String msg;

    public DBOperationException(ResponseCode responseCode) {
        super(responseCode.cnMsg());
        this.code = responseCode.code();
        this.msg = responseCode.cnMsg();
    }


}
