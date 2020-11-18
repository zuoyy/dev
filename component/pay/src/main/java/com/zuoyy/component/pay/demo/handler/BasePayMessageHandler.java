package com.zuoyy.component.pay.demo.handler;

import com.zuoyy.component.pay.common.api.PayMessageHandler;
import com.zuoyy.component.pay.common.api.PayService;
import com.zuoyy.component.pay.common.bean.PayMessage;

/**
 *
 * Created by ZaoSheng on 2016/6/1.
 */
public abstract class BasePayMessageHandler<M extends PayMessage, S extends PayService> implements PayMessageHandler<M, S> {
    //支付账户id
    private Integer payId;

    public BasePayMessageHandler(Integer payId) {
        this.payId = payId;
    }

    public Integer getPayId() {
        return payId;
    }
}
