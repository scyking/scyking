package com.scyking.ucenter;

import com.scyking.common.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UcenterApplicationTests {

    @Autowired
    JwtUtils jwtUtils;

    @Test
    void contextLoads() {
    }

}
