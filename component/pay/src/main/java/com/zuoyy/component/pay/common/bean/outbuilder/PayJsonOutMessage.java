package com.zuoyy.component.pay.common.bean.outbuilder;

import com.zuoyy.component.pay.common.bean.MsgType;
import com.zuoyy.component.pay.common.bean.PayOutMessage;

/**
 */
public class PayJsonOutMessage extends PayOutMessage{

    public PayJsonOutMessage() {
        this.msgType = MsgType.json.name();
    }

    @Override
    public String toMessage() {
        return getContent();
    }


}
