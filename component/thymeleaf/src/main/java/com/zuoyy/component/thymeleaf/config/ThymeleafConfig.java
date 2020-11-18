package com.zuoyy.component.thymeleaf.config;

import com.zuoyy.component.thymeleaf.ZuoyyDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuo
 */
@Configuration
public class ThymeleafConfig {

    /**
     * 配置自定义的CusDialect，用于整合thymeleaf模板
     */
    @Bean
    public ZuoyyDialect getTimoDialect(){
        return new ZuoyyDialect();
    }
}
