package com.scyking.sgateway.filter;

import cn.hutool.json.JSONUtil;
import com.scyking.common.base.HttpResult;
import com.scyking.common.base.UserInfo;
import com.scyking.common.utils.Constants;
import com.scyking.common.utils.ResponseUtils;
import com.scyking.sgateway.client.UserAuthClient;
import com.scyking.sgateway.utils.GatewayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
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
 * 网关统一鉴权
 * <p>
 * User Account and Authentication
 *
 * @author scyking
 **/
@Component
public class UaaFilter implements GlobalFilter, Ordered {

    @Value("${scyking.uaa.enable:false}")
    boolean enable;

    @Autowired
    UserAuthClient userAuthClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!enable) {
            // 如果未开启 封装模拟转发信息 方便调试
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(Constants.HEADER_USER_ID, "9527")
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        }
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        // 在忽略的 url 里，则跳过
        if (ignore(requestUrl)) {
            return chain.filter(exchange);
        }
        String headerToken = exchange.getRequest().getHeaders().getFirst(Constants.HEADER_TOKEN);
        // 未获取到token信息，失败
        if (!StringUtils.hasText(headerToken)) {
            return noPower(exchange);
        }
        HttpResult<UserInfo> resp = userAuthClient.checkUserToken(headerToken);
        // 鉴权失败！
        if (!ResponseUtils.hasData(resp)) {
            return noPower(exchange);
        }
        // 封装转发信息
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header(Constants.HEADER_USER_ID, resp.getData().getUserId().toString())
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
        return noPower(serverWebExchange, "token失效，请重新登录！");
    }

    private Mono<Void> noPower(ServerWebExchange serverWebExchange, String message) {
        // 响应结果数据
        HttpResult result = HttpResult.error().code(HttpStatus.UNAUTHORIZED.value()).msg(message);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(JSONUtil.parse(result).toStringPretty().getBytes(StandardCharsets.UTF_8));
        // 响应
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));

    }

    @Override
    public int getOrder() {
        return GatewayConstants.ORDER_UAA_FILTER;
    }
}
