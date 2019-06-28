package com.module.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 参考实现
 * http://www.leftso.com/blog/221.html
 *
 * @author wongdong
 * @date 2019/4/30
 */
public class AppTokenUtil {
    private static final String USER_AGENT_FILED = "userAgent";
    private static final String USER_TYPE_FILED = "userType";
    private static final String USER_MOBILE_FILED = "mobile";
    private static final String USER_IP_FILED = "mobile";

    private static final String HMAC256_SECRET = "JYWHSECRET2019";
    private static Algorithm algorithm = Algorithm.HMAC256(HMAC256_SECRET);
    /**
     * 失效时间30天
     */
    private static long EXPIRED_DAYS = 30;

    public static String ecode(AppTokenDto token) {
        JWTCreator.Builder builder = JWT.create()
                //主体
                .withSubject(token.getSubject())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plus(EXPIRED_DAYS, ChronoUnit.DAYS)))
                .withClaim(USER_AGENT_FILED, token.getUserAgent())
                .withClaim(USER_TYPE_FILED, token.getAppUserType().toString())
                .withClaim(USER_IP_FILED, token.getIp());
        if (token.getAppUserType() == AppUserType.USER) {
            builder.withClaim(USER_MOBILE_FILED, token.getMobile());
        }
        return builder.sign(algorithm);

    }


    public static AppTokenDto decode(String token) throws JWTDecodeException {
        DecodedJWT decode = JWT.decode(token);
        AppUserType appUserType = AppUserType.valueOf(decode.getClaim(USER_TYPE_FILED).asString());
        AppTokenDto.AppTokenDtoBuilder appTokenDtoBuilder = AppTokenDto.builder()
                .appUserType(AppUserType.valueOf(decode.getClaim(USER_TYPE_FILED).asString()))
                .subject(decode.getSubject())
                .userAgent(decode.getClaim(USER_AGENT_FILED).asString())
                .ip(decode.getClaim(USER_AGENT_FILED).asString());
        if (appUserType == AppUserType.USER) {
            appTokenDtoBuilder.mobile(decode.getClaim(USER_IP_FILED).asString());
        }
        return appTokenDtoBuilder.build();
    }

    /**
     * 验证token
     * 过期 篡改
     *
     * @param token
     * @throws JWTVerificationException
     */
    public static void verifier(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }

}
