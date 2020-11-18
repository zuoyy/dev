package com.zuoyy.component.pay.common.bean.outbuilder;

import com.zuoyy.component.pay.common.bean.MsgType;
import com.zuoyy.component.pay.common.bean.PayOutMessage;

/**
 */
public class PayTextOutMessage extends PayOutMessage{

    public PayTextOutMessage() {
        this.msgType = MsgType.text.name();
    }

    @Override
    public String toMessage() {
        return getContent();
    }
}
