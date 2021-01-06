package com.scyking.common.utils;

import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author scyking
 * @description
 **/
public class Base64Utils {

    public static String encode(String str) {
        Assert.notNull(str, "待base64转码字符串不能为null！");
        return new String(Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decode(String base64Str) {
        Assert.notNull(base64Str, "待base64解码字符串不能为null！");
        return new String(Base64.getDecoder().decode(base64Str), StandardCharsets.UTF_8);
    }
}
