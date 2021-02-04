package com.scyking.slog;

import com.scyking.common.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SLogApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void msgProducer() {
        rabbitTemplate.convertAndSend(Constants.LOG_DIRECT_EXCHANGE,
                Constants.LOG_DIRECT_ROUTING_KEY, "hello world!");
    }

}
