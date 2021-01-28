package com.scyking.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

/**
 * Json web token (JWT)
 * <p>
 * JWT构成：
 * 1. header：
 * 1.1 typ: 声明类型，这里是JWT
 * 1.2 alg: 声明加密算法，通常直接使用 HMAC SHA256
 * 2. payload（存放有效信息）：
 * 2.1 标准中注册的声明
 * - iss: jwt签发者
 * - sub: jwt所面向的用户
 * - aud: 接收jwt的一方
 * - exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * - nbf: 定义在什么时间之前，该jwt都是不可用的.
 * - iat: jwt的签发时间
 * - jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 * 2.2 公共的声明(添加用户的相关信息或其他业务需要的必要信息)
 * 2.3 私有的声明(提供者和消费者所共同定义的声明)
 * 3. signature：
 * 3.1 header（base64加密）
 * 3.2 payload（base64加密）
 * 3.3 secret（保存在server端，用来签发和认证）
 * </p>
 *
 * @author scyking
 **/
public class JwtUtils {

    private static final long expire = 43200;

    /**
     * 签发jwt:
     * 1. sub(用户标识)
     */
    public String signJwt(String userId, String secret) {
        Assert.hasText(userId, "生成token，用户id不能为空！");
        Assert.hasText(secret, "生成token，秘钥不能为空！");
        long currentTimeMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + this.expire * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证jwt:
     * 1. 是否能够正常解析
     * 2. 是否过期
     *
     * @return true 验证成功，抛出异常或者false则失败
     */
    public Claims verifyJwt(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 不通过秘钥解析payload信息
     *
     * @param jwt
     * @return
     */
    public Map getJwtPayload(String jwt) throws JsonProcessingException {
        Assert.notNull(jwt, "待解析jwt不能为空！");
        String[] jwtStr = jwt.split("\\.");
        if (jwtStr.length < 3) {
            throw new UnsupportedJwtException("待解析jwt格式错误！");
        }
        // 解析payload部分，并返回结果
        return JsonUtils.json2Map(Base64Utils.decode(jwtStr[1]));
    }

}
