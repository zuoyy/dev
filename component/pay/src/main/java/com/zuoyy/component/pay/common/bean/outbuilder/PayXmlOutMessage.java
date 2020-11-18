package com.zuoyy.component.pay.common.bean.outbuilder;

import com.zuoyy.component.pay.common.bean.MsgType;
import com.zuoyy.component.pay.common.bean.PayOutMessage;

/**
 */
public class PayXmlOutMessage extends PayOutMessage{

    private String code;

    public PayXmlOutMessage() {
        this.msgType = MsgType.xml.name();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toMessage() {
       return "<xml><return_code><![CDATA[" + code + "]]></return_code><return_msg><![CDATA[" + content
                + "]]></return_msg></xml>";
    }
}
