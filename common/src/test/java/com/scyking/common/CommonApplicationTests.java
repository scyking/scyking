package com.scyking.common;

import com.scyking.common.enums.SKeyLengthEnum;
import com.scyking.common.utils.KeyGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class CommonApplicationTests {

    @Test
    void contextLoads() {
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
