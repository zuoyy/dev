package com.zuoyy.component.actionLog.action.model;

import com.zuoyy.modules.common.enums.ActionLogEnum;
import lombok.Getter;

/**
 * @author zuo
 */
@Getter
public class LoginType extends BusinessType{
    // 日志类型
    protected int type = ActionLogEnum.LOGIN.getCode();

    public LoginType(String message) {
        super(message);
    }

    public LoginType(String name, String message) {
        super(name, message);
    }
}
