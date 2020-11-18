package com.zuoyy.component.pay.common.bean.outbuilder;

import com.zuoyy.component.pay.common.bean.PayOutMessage;

/**
 */
public class TextBuilder extends BaseBuilder<TextBuilder, PayOutMessage> {
    private String content;

    public TextBuilder content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public PayOutMessage build() {
        PayTextOutMessage message = new PayTextOutMessage();
        setCommon(message);
        message.setContent(content);
        return message;
    }
}
