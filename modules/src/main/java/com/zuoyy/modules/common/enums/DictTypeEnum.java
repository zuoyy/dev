package com.zuoyy.modules.common.enums;

import lombok.Getter;


@Getter
public enum DictTypeEnum {

    VALUE((byte)1, "字符值"),
    KEY_VALUE((byte)2, "键值对"),
    ENUM_VALUE((byte)3, "枚举类");

    private Byte code;

    private String message;

    DictTypeEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}

