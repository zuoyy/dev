package com.zuoyy.common.enums;

import com.zuoyy.common.constant.StatusConst;
import lombok.Getter;

/**
 * 数据库字段状态枚举
 * @author zuo
 */
public enum StatusEnum {

    OK(StatusConst.OK, "启用"),
    FREEZED(StatusConst.FREEZED, "冻结"),
    DELETE(StatusConst.DELETE, "删除");

    public final int code;

    public final String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusEnum getItem(Integer code) {
        for(StatusEnum s : StatusEnum.values()) {
            if(s.code==code) {
                return s;
            }
        }
        return null;
    }

}

