package com.scyking.slog.config;

import com.scyking.common.config.Swagger3Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注入公共模块配置
 *
 * @author scyking
 **/
@Configuration
public class CommonConfig {

    @Bean
    public Swagger3Config getSwagger3Config() {
        return new Swagger3Config();
    }
}
