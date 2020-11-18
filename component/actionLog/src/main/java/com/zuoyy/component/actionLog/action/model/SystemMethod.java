package com.zuoyy.component.actionLog.action.model;

import com.zuoyy.modules.common.enums.ActionLogEnum;
import lombok.Getter;

/**
 * @author zuo
 */
@Getter
public class SystemMethod extends BusinessMethod{
    // 日志类型
    protected int type = ActionLogEnum.SYSTEM.getCode();

    public SystemMethod(String method) {
        super(method);
    }

    public SystemMethod(String name, String method) {
        super(name, method);
    }
}
