package com.scyking.sgateway.filter;

import com.scyking.common.base.BaseResponse;
import com.scyking.sgateway.client.UserAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author scyking
 * @description
 **/
@Component
public class UaaFilter implements GlobalFilter {

    @Autowired
    UserAuthClient userAuthClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        // 在忽略的 url 里，则跳过
        if (ignore(requestUrl)) {
            return chain.filter(exchange);
        }
        String headerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 未获取到token信息，失败
        if (!StringUtils.hasText(headerToken)) {
            return noPower(exchange, "认证失败，未获取到token信息！");
        }
        BaseResponse resp = userAuthClient.checkUserToken(headerToken);
        // 鉴权失败！
        if (resp == null || resp.isError()) {
            return noPower(exchange);
        }
        // 封装转发信息
        //todo something
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("Authenticator", headerToken)
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    private boolean ignore(String path) {
        List<String> ignoreUrl = new ArrayList<>();
        ignoreUrl.add("/swagger-ui/**");
        ignoreUrl.add("/v3/api-docs");
        ignoreUrl.add("/swagger-resources/**");
        return ignoreUrl.stream()
                .map(url -> url.replace("/**", ""))
                .anyMatch(path::startsWith);
    }

    private Mono<Void> noPower(ServerWebExchange serverWebExchange) {
        return noPower(serverWebExchange, "鉴权失败！");
    }

    private Mono<Void> noPower(ServerWebExchange serverWebExchange, String message) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        String result = "{\"code\":" + HttpStatus.UNAUTHORIZED.value() + ",\"msg\": \"" + message + "\"}";
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));

    }
}
