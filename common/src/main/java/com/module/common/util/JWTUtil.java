package com.module.common.util;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Map;

/**
 * 参考实现
 * http://www.leftso.com/blog/221.html
 * @author wongdong
 * @date 2019/4/30
 */
public class JWTUtil {
    private static final String USER_AGENT_FILED="User-Agent";
    private static final String HMAC256_SECRET="JYWHSECRET2019";
    private static Algorithm algorithm = Algorithm.HMAC256(HMAC256_SECRET);


    public static String ecode(String userAgent,String userId){
        return JWT.create()
                .withSubject(userId)
                .withJWTId(IdUtil.fastSimpleUUID())
                .sign(algorithm);
    }


    public static DecodedJWT dcode(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }

    public static void verifier(String token,String userId,String jwtId) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withSubject(userId)
                .withJWTId(jwtId)
                .build();
        verifier.verify(token);
    }

}
