package com.zuoyy.component.actionLog.exception;

import com.zuoyy.common.exception.advice.ResultExceptionAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 将异常切入程序添加到异常通知器中
 * @author zuo
 */
@Configuration
public class ActionLogProceedAdviceConfig {

    @Bean
    public ActionLogProceedAdvice actionLogProceedAdvice(ResultExceptionAdvice advice) {
        ActionLogProceedAdvice authorization = new ActionLogProceedAdvice();
        advice.putProceed(authorization);
        return authorization;
    }
}
