package com.zuoyy.component.actionLog.exception;

import com.zuoyy.common.exception.advice.ExceptionAdvice;
import com.zuoyy.component.actionLog.action.SystemAction;
import com.zuoyy.component.actionLog.annotation.ActionLog;

/**
 * 运行时抛出的异常进行日志记录
 * @author zuo
 */
public class ActionLogProceedAdvice implements ExceptionAdvice {

    @Override
    @ActionLog(key = SystemAction.RUNTIME_EXCEPTION, action = SystemAction.class)
    public void run(RuntimeException e) {}
}
