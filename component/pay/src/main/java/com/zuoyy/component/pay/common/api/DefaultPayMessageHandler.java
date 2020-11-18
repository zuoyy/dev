package com.zuoyy.component.pay.common.api;

import com.alibaba.fastjson.JSON;
import com.zuoyy.component.pay.common.bean.PayMessage;
import com.zuoyy.component.pay.common.bean.PayOutMessage;
import com.zuoyy.component.pay.common.exception.PayErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * 默认处理支付回调消息的处理器接口
 *
 */
public class DefaultPayMessageHandler implements PayMessageHandler {

    protected final Log LOG = LogFactory.getLog(DefaultPayMessageHandler.class);
    /**
     * @param payMessage 支付消息
     * @param context    上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param payService 支付服务
     * @return xml, text格式的消息，如果在异步规则里处理的话，可以返回null
     */
    @Override
    public PayOutMessage handle(PayMessage payMessage, Map context, PayService payService) throws PayErrorException {
        if (LOG.isInfoEnabled()) {
            LOG.info("回调支付消息处理器，回调消息：" + JSON.toJSONString(payMessage));
        }
        return payService.successPayOutMessage(payMessage);
    }
}
