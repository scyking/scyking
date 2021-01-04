package com.scyking.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     JWT构成：
 *     1. header（base64加密）：
 *          1.1 声明类型，这里是JWT
 *          1.2 声明加密算法，通常直接使用 HMAC SHA256
 *     2. payload（存放有效信息，base64加密）：
 *          2.1 标准中注册的声明
 *          2.2 公共的声明
 *          2.3 私有的声明
 *     3. signature：
 *          3.1 header
 *          3.2 payload
 *          3.3 secret
 * </p>
 * @author scyking
 * @description Json web token (JWT)
 **/
@Data
@Slf4j
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;
}
