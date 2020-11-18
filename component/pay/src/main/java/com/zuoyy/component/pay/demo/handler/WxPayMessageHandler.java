package com.zuoyy.component.pay.demo.handler;

import com.zuoyy.component.pay.common.api.PayService;
import com.zuoyy.component.pay.common.bean.PayOutMessage;
import com.zuoyy.component.pay.common.exception.PayErrorException;
import com.zuoyy.component.pay.weixin.bean.WxPayMessage;

import java.util.Map;

/**
 * 微信支付回调处理器
 */
public class WxPayMessageHandler extends BasePayMessageHandler<WxPayMessage, PayService> {




    public WxPayMessageHandler(Integer payId) {
        super(payId);
    }


    @Override
    public PayOutMessage handle(WxPayMessage payMessage, Map context, PayService payService) throws PayErrorException {
        //交易状态
        if ("SUCCESS".equals(payMessage.getPayMessage().get("result_code"))){
            /////这里进行成功的处理

            return  payService.getPayOutMessage("SUCCESS", "OK");
        }

        return  payService.getPayOutMessage("FAIL", "失败");
    }
}
