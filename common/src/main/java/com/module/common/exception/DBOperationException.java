package com.module.common.exception;

import com.module.common.ResponseCode;
import lombok.Data;

@Data
public class DBOperationException extends RuntimeException {
    private ResponseCode responseCode;


    public DBOperationException(ResponseCode responseCode) {
        this.responseCode=responseCode;
    }


}
