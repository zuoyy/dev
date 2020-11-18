package com.zuoyy.common.exception;

import com.zuoyy.common.exception.advice.ResultExceptionAdvice;
import com.zuoyy.common.utils.SpringContextUtil;
import com.zuoyy.common.vo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * 全局统一异常处理
 * @author zuo
 */
@ControllerAdvice
@Slf4j
public class ResultExceptionHandler {

    // 拦截自定义异常
    @ExceptionHandler(ResultException.class)
    @ResponseBody
    public JsonResponse resultException(ResultException e){
        return JsonResponse.error(e.getCode(), e.getMessage());
    }

    // 拦截表单验证异常
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResponse bindException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        return JsonResponse.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    // 拦截未知的运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResponse runtimeException(RuntimeException e) {
        ResultExceptionAdvice resultExceptionAdvice = SpringContextUtil.getBean(ResultExceptionAdvice.class);
        resultExceptionAdvice.runtimeException(e);
        log.error("【系统异常】", e);
        return JsonResponse.error(500, "未知错误：EX4399");
    }
}
