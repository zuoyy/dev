package com.zuoyy.api.system.controller;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.exception.ResultException;
import com.zuoyy.common.utils.EncryptUtil;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.jwt.annotation.IgnorePermissions;
import com.zuoyy.component.jwt.config.properties.JwtProjectProperties;
import com.zuoyy.component.jwt.enums.JwtResultEnums;
import com.zuoyy.component.jwt.utlis.JwtUtil;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认登录验证控制器
 * 说明：默认采用系统用户进行登录验证
 * @author zuo
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtProjectProperties properties;

    @Autowired
    private UserService userService;

    @IgnorePermissions
    @PostMapping("/")
    public JsonResponse auth(String username, String password){
        // 根据用户名获取系统用户数据
        User user = userService.findByName(username);
        if (user == null) {
            throw new ResultException(JwtResultEnums.AUTH_REQUEST_ERROR);
        } else if (user.getStatus().equals(StatusEnum.FREEZED.code)){
            throw new ResultException(JwtResultEnums.AUTH_REQUEST_LOCKED);
        } else {
            // 对明文密码加密处理
            String encrypt = EncryptUtil.encrypt(password, user.getSalt());
            // 判断密码是否正确
            if (encrypt.equals(user.getPassword())) {
                String token = JwtUtil.getToken(username, properties.getSecret(), properties.getExpired());
                return JsonResponse.success((Object) token);
            } else {
                throw new ResultException(JwtResultEnums.AUTH_REQUEST_ERROR);
            }
        }
    }
}
