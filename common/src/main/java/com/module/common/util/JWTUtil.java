package com.module.common.util;

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
    private static final String USER_AGENT_FILED="userAgent";
    private static final String HMAC256_SECRET="JYWHSECRET2019";
    Algorithm algorithm = Algorithm.HMAC256(HMAC256_SECRET);


    public String ecode(String userAgent,String userId){
        return JWT.create()
                .withIssuer(userId)
                .withClaim(USER_AGENT_FILED,userAgent)
                .withJWTId(UUIDWorker.uuid())
                .sign(algorithm);
    }


    public DecodedJWT dcode(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }
    public void verifier(String token,String userAgent,String userId,String jwtId) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(USER_AGENT_FILED, userAgent)
                .withIssuer(userId)
                .withJWTId(jwtId)
                .build();
        verifier.verify(token);
    }

}
