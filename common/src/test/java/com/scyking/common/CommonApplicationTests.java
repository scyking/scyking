package com.scyking.common;

import com.scyking.common.enums.SKeyLengthEnum;
import com.scyking.common.utils.Base64Utils;
import com.scyking.common.utils.JwtUtils;
import com.scyking.common.utils.KeyGeneratorUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class CommonApplicationTests {

    @Autowired
    JwtUtils jwtUtils;

    @Test
    void contextLoads() {
    }

    @Test
    void jwtTest() {
        String secret = "TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";
        String token = jwtUtils.signJwt("1", secret);
        System.out.println(token);
        String[] base64Str = token.split("\\.");
        System.out.println(Base64Utils.decode(base64Str[0]));
        System.out.println(Base64Utils.decode(base64Str[1]));
        System.out.println(jwtUtils.verifyJwt(token, secret));
    }

    @Test
    void keyGenerator() {
        String seed = "test";
        try {
            String key = KeyGeneratorUtils.genAESKey(SKeyLengthEnum._128, seed);
            System.out.println(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
