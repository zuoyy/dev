package com.zuoyy.component.pay.common.exception;

import com.zuoyy.component.pay.common.bean.result.PayError;

/**
 */
public class PayErrorException extends RuntimeException  {

    private PayError error;

    public PayErrorException(PayError error) {
        super(error.getString());
        this.error = error;
    }


    public PayError getPayError() {
        return error;
    }
}
