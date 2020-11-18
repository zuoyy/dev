package com.zuoyy.component.actionLog.action.model;

import com.zuoyy.modules.common.enums.ActionLogEnum;
import lombok.Getter;

/**
 * @author zuo
 */
@Getter
public class BusinessType extends ActionModel{
    // 日志名称
    protected String name;
    // 日志消息
    protected String message;
    // 日志类型
    protected int type = ActionLogEnum.BUSINESS.getCode();

    /**
     * 只构建日志消息，日志名称有日志注解name定义
     * @param message 日志消息
     */
    public BusinessType(String message) {
        this.message = message;
    }

    /**
     * 构建日志名称和日志消息
     * @param name 日志名称
     * @param message 日志消息
     */
    public BusinessType(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
