package com.zuoyy.common.exception.advice;

/**
 * 异常通知器接口
 * @author zuo
 */
public interface ExceptionAdvice {
    public void run(RuntimeException e);
}
