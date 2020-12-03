package com.zys.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    /**
     * 设置过期时间及密匙
     * CALENDAR_INTERVAL 有效时间
     * SECRET_KEY 密匙
     */
    public static final int CALENDAR_INTERVAL = 60;
    private static final String sign = "6A50A18D70FA63636645C65459F1D78A";

    /**
     * 创建Token
     *
     * @param map 自己需要存储进token中的信息
     * @return token
     */
    public static String createToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,CALENDAR_INTERVAL);
        JWTCreator.Builder builder = JWT.create();

        //payloda
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        // 设置过期时间和秘钥
        String token = builder.withExpiresAt(instance.getTime())
                     .sign(Algorithm.HMAC256(JwtUtils.sign));
        return token;
    }

    /**
     * 验证、解析Token
     *
     * @param token 用户提交的token
     * @return 该token中的信息
     */
    public static DecodedJWT  verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(sign)).build().verify(token);

}

}
