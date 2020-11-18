package com.zuoyy.component.pay.common.bean.outbuilder;

import com.zuoyy.component.pay.common.bean.PayOutMessage;

/**
 * source chanjarster/weixin-java-tools
 *
 */
public abstract class BaseBuilder<BuilderType, ValueType> {


    public abstract ValueType build();

    public void setCommon(PayOutMessage m) {

    }

}