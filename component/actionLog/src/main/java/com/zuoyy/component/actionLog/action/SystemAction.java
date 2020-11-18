package com.zuoyy.component.actionLog.action;

import com.zuoyy.component.actionLog.action.base.ActionMap;
import com.zuoyy.component.actionLog.action.base.ResetLog;
import com.zuoyy.component.actionLog.action.model.SystemMethod;
import com.zuoyy.modules.system.domain.ActionLog;

/**
 * @author zuo
 */
public class SystemAction extends ActionMap {

    public static final String RUNTIME_EXCEPTION = "runtime_exception";

    @Override
    public void init() {
        // 系统异常行为
        putMethod(RUNTIME_EXCEPTION, new SystemMethod("系统异常","runtimeException"));
    }

    // 系统异常行为方法
    public void runtimeException(ResetLog resetLog){
        RuntimeException runtime = (RuntimeException) resetLog.getParam("e");
        StringBuilder message = new StringBuilder();
        message.append(runtime.toString());
        StackTraceElement[] stackTrace = runtime.getStackTrace();
        for (StackTraceElement stack : stackTrace) {
            message.append("\n\t").append(stack.toString());
        }
        ActionLog actionLog = resetLog.getActionLog();
        actionLog.setMessage(String.valueOf(message));
    }
}
