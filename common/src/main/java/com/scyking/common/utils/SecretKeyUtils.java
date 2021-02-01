package com.scyking.common.utils;

import com.scyking.common.enums.SKeyLengthEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 秘钥对生成工具类
 *
 * @author scyking
 * @description
 * @createTime 2020/12/29 17:08
 **/
@Slf4j
public class SecretKeyUtils {

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    /**
     * 随机生成秘钥
     *
     * @param sKeyLengthEnum 生成秘钥位数(128, 192或256)
     * @return
     */
    public static String genASEKey(SKeyLengthEnum sKeyLengthEnum) throws NoSuchAlgorithmException {
        Assert.notNull(sKeyLengthEnum, "秘钥位数sKeyLengthEnum不能为 null");
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(sKeyLengthEnum.getLength());
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        return byteToHexString(b);
    }

    /**
     * 使用指定的字符串生成秘钥
     *
     * @param seed
     * @param sKeyLengthEnum
     */
    public static String genAESKey(SKeyLengthEnum sKeyLengthEnum, String seed) throws NoSuchAlgorithmException {
        Assert.notNull(sKeyLengthEnum, "秘钥位数sKeyLengthEnum不能为 null");
        Assert.notNull(seed, "生成秘钥种子seed不能为 null");
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
        kg.init(sKeyLengthEnum.getLength(), new SecureRandom(seed.getBytes()));
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        return byteToHexString(b);
    }

    /**
     * byte数组转化为16进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byteToHexString(byte[] bytes) {
        StringBuilder r = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 一个字节(byte)对应8位(bit) 即两个2个十六进制字符
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }
}
