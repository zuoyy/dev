package com.zuoyy.component.jwt.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt配置项
 * @author zuo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "project.jwt")
public class JwtProjectProperties {
    // jwt秘钥
    private String secret = "mySecret";
    // 过期时间(天)，默认3天
    private Integer expired = 3;

}
