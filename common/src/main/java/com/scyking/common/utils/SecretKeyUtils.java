package com.scyking.common.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.crypto.SecretKey;
import javax.validation.constraints.NotNull;

/**
 * 秘钥对生成工具类
 *
 * @author scyking
 * @description
 * @createTime 2020/12/29 17:08
 **/
@Slf4j
public class SecretKeyUtils {

    /**
     * 生成秘钥
     *
     * @return
     */
    public static String genASEKey() {
        SecretKey sk = KeyUtil.generateKey(SymmetricAlgorithm.AES.getValue());
        return toHexString(sk);
    }

    /**
     * 使用指定的字符串(key)生成秘钥
     *
     * @param seed
     */
    public static String genAESKey(String seed) {
        Assert.notNull(seed, "生成秘钥种子seed不能为 null");
        // SecureRandom是生成安全随机数序列，seed是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
        SecretKey sk = KeyUtil.generateKey(SymmetricAlgorithm.AES.getValue(), seed.getBytes());
        return toHexString(sk);
    }

    /**
     * 获取16进制字符串
     *
     * @param sk 秘钥
     * @return
     */
    private static String toHexString(@NotNull SecretKey sk) {
        byte[] bytes = sk.getEncoded();
        char[] chars = HexUtil.encodeHex(bytes);
        return new String(chars);
    }
}
