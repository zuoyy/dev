package com.zuoyy.component.pay.common.api;

import com.zuoyy.component.pay.common.bean.PayMessage;
import com.zuoyy.component.pay.common.bean.PayOutMessage;
import com.zuoyy.component.pay.common.exception.PayErrorException;

import java.util.Map;


/**
 * 处理支付回调消息的处理器接口
 *
 */
public interface PayMessageHandler<M extends PayMessage, S extends PayService> {

    /**
     * 处理支付回调消息的处理器接口
     * @param payMessage 支付消息
     * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param payService 支付服务
     * @return xml,text格式的消息，如果在异步规则里处理的话，可以返回null
     * @throws PayErrorException 支付错误异常
     */
    PayOutMessage handle(M payMessage,Map context,S payService) throws PayErrorException;

}
