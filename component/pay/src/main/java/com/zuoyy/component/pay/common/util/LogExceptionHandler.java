package com.zuoyy.component.pay.common.util;

import com.zuoyy.component.pay.common.api.PayErrorExceptionHandler;
import com.zuoyy.component.pay.common.exception.PayErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * LogExceptionHandler 日志处理器
 */
public class LogExceptionHandler implements PayErrorExceptionHandler {

    protected final Log log = LogFactory.getLog(PayErrorExceptionHandler.class);

    @Override
    public void handle(PayErrorException e) {

        log.error("Error happens", e);

    }

}
