package com.scyking.slog.mq;

import com.scyking.common.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 消费者
 *
 * @author scyking
 **/
@Slf4j
@Component
public class MsgConsumer {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = Constants.LOG_DIRECT_QUEUE, durable = "true"),
            exchange = @Exchange(value = Constants.LOG_DIRECT_EXCHANGE), key = Constants.LOG_DIRECT_ROUTING_KEY)})
    @RabbitHandler
    public void processMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received message : " + msg);
    }
}
