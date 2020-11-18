package com.zuoyy.component.actionLog.action.model;

import com.zuoyy.modules.common.enums.ActionLogEnum;
import lombok.Getter;

/**
 * @author zuo
 */
@Getter
public class SystemType extends BusinessType{
    // 日志类型
    protected int type = ActionLogEnum.SYSTEM.getCode();

    public SystemType(String message) {
        super(message);
    }

    public SystemType(String name, String message) {
        super(name, message);
    }
}
