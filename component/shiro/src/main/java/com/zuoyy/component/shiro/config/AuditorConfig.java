package com.zuoyy.component.shiro.config;

import com.zuoyy.modules.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 审核员自动赋值配置
 * @author zuo
 */
@Configuration
public class AuditorConfig implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return Optional.ofNullable(user);
    }
}
