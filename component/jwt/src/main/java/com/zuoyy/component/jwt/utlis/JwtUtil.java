package com.zuoyy.component.jwt.utlis;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zuoyy.common.exception.ResultException;
import com.zuoyy.common.utils.ToolUtil;
import com.zuoyy.component.jwt.enums.JwtResultEnums;
import com.zuoyy.modules.system.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zuo
 */
public class JwtUtil {

    /**
     * 生成JwtToken
     * @param username 用户名
     * @param secret 秘钥
     * @param amount 过期天数
     */
    public static String getToken(String username, String secret, int amount){
        User user = new User();
        user.setUsername(username);
        return getToken(user, secret, amount);
    }

    /**
     * 生成JwtToken
     * @param user 用户对象
     * @param secret 秘钥
     * @param amount 过期天数
     */
    public static String getToken(User user, String secret, int amount){
        // 过期时间
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, amount);

        // 随机Claim
        String random = ToolUtil.getRandomString(6);

        // 创建JwtToken对象
        String token="";
        token= JWT.create()
                .withSubject(user.getUsername())    // 用户名
                .withIssuedAt(new Date())           // 发布时间
                .withExpiresAt(ca.getTime())        // 过期时间
                .withClaim("ran", random)     // 自定义随机Claim
                .sign(getSecret(secret, random));

        return token;
    }

    /**
     * 获取请求对象中的token数据
     */
    public static String getRequestToken(HttpServletRequest request){
        // 获取JwtTokens失败
        String authorization = request.getHeader("token");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ResultException(JwtResultEnums.TOKEN_ERROR);
        }
        return authorization.substring(7);
    }

    /**
     * 解析JwtToken，生成用户对象
     * @param token JwtToken数据
     */
    public static User parseToken(String token){
        String sub = JWT.decode(token).getSubject();
        User user = new User();
        user.setUsername(sub);
        return user;
    }

    /**
     * 验证JwtToken
     * @param token JwtToken数据
     * @return true 验证通过
     * @exception TokenExpiredException Token过期
     * @exception JWTVerificationException 令牌无效（验证不通过）
     */
    public static void verifyToken(String token, String secret) throws JWTVerificationException {
        String ran = JWT.decode(token).getClaim("ran").asString();
        JWTVerifier jwtVerifier = JWT.require(getSecret(secret, ran)).build();
        jwtVerifier.verify(token);
    }

    /**
     * 生成Secret混淆数据
     */
    private static Algorithm getSecret(String secret, String random){
        String salt = "不辜负每一份热情，不讨好每一份冷漠。";
        return Algorithm.HMAC256(secret + salt + "一毛哥" + random);
    }
}
