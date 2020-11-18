package com.zuoyy.modules.common.enums;

import lombok.Getter;


@Getter
public enum ActionLogEnum {

    BUSINESS(1, "业务"),
    LOGIN(2, "登录"),
    SYSTEM(3, "系统");

    private int code;

    private String message;

    ActionLogEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
