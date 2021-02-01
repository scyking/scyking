package com.scyking.slog.config;

import com.scyking.common.utils.Constant;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author scyking
 */
@Configuration
public class RabbitConfig {

    //创建队列
    @Bean
    public Queue createDirectQueue() {
        return new Queue(Constant.LOG_DIRECT_QUEUE);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(Constant.LOG_DIRECT_EXCHANGE);
    }

}