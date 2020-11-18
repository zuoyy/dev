package com.zuoyy.component.actionLog.annotation;

import java.lang.annotation.*;

/**
 * 控制器实体参数注解
 * @author zuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
public @interface EntityParam {
}
