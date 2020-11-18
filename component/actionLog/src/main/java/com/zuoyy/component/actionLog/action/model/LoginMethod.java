package com.zuoyy.component.actionLog.action.model;

import com.zuoyy.modules.common.enums.ActionLogEnum;
import lombok.Getter;

/**
 * @author zuo
 */
@Getter
public class LoginMethod extends BusinessMethod{
    // 日志类型
    protected int type = ActionLogEnum.LOGIN.getCode();

    public LoginMethod(String method) {
        super(method);
    }

    public LoginMethod(String name, String method) {
        super(name, method);
    }
}
