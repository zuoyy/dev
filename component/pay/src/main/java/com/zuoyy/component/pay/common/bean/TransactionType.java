package com.zuoyy.component.pay.common.bean;

/**
 * 交易类型
 */
public interface TransactionType {
    /**
     * 获取交易类型
     * @return 交易类型
     */
     String getType();

    /**
     * 获取接口
     * @return 接口
     */
     String getMethod();
}

