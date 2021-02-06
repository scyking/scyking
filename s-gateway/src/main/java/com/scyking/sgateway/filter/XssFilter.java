package com.scyking.sgateway.filter;

import com.scyking.sgateway.utils.GatewayConstants;
import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 跨站脚本攻击（XSS） 过滤
 *
 * @author scyking
 **/
@Component
public class XssFilter implements GlobalFilter, Ordered {

    private List<XssWhiteUrl> whiteUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpMethod method = serverHttpRequest.getMethod();
        String requestUrl = serverHttpRequest.getURI().getRawPath();
        HttpHeaders headers = serverHttpRequest.getHeaders();
        // 非json请求、白名单中请求直接跳过
        if (headers.getContentType() != MediaType.APPLICATION_JSON
                || white(requestUrl, method)) {
            return chain.filter(exchange);
        }
        // GET方法，进行拦截
        if (HttpMethod.GET != method) {

        }
        // DataBufferUtils.join(serverHttpRequest.getBody()).flatMap();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return GatewayConstants.ORDER_XSS_FILTER;
    }

    /**
     * 是否在白名单中
     *
     * @param url    路由
     * @param method 请求方式
     * @return true/false
     */
    private boolean white(String url, HttpMethod method) {
        XssWhiteUrl xssWhiteUrl = new XssWhiteUrl(url, method);
        return whiteUrls != null && whiteUrls.contains(xssWhiteUrl);
    }

    /**
     * 字节数组转DataBuffer
     *
     * @param bytes 字节数组
     * @return DataBuffer
     */
    private DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    @Data
    @Validated
    private static class XssWhiteUrl {

        @NotEmpty
        private String url;
        @NotEmpty
        private HttpMethod method;

        XssWhiteUrl(String url, HttpMethod method) {
            this.url = url;
            this.method = method;
        }
    }
}
