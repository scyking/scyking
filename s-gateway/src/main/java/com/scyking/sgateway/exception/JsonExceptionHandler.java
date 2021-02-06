package com.scyking.sgateway.exception;

import com.scyking.common.base.HttpResult;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * 实现{@link ErrorWebExceptionHandler}接口，重写{@code handle}方法，进行网关统一异常处理
 *
 * @author scyking
 * @see org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
 **/
@Slf4j
public class JsonExceptionHandler implements ErrorWebExceptionHandler {

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    // 存储处理异常后的信息
    private ThreadLocal<HttpResult> exceptionHandlerResult = new ThreadLocal<>();

    //*******************************************************************************/
    //*****************   参考AbstractErrorWebExceptionHandler   ********************/
    //*******************************************************************************/
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    private Mono<? extends Void> write(ServerWebExchange exchange,
                                       ServerResponse response) {
        exchange.getResponse().getHeaders()
                .setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    private class ResponseContext implements ServerResponse.Context {

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return JsonExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return JsonExceptionHandler.this.viewResolvers;
        }
    }
    //*******************************************************************************/
    //*****************   参考AbstractErrorWebExceptionHandler   ********************/
    //*******************************************************************************/

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        handleException(ex);
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));
    }

    private void handleException(Throwable ex) {
        // 打印错误堆栈信息 方便调试
        ex.printStackTrace();
        HttpResult response = HttpResult.error();
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            response.code(responseStatusException.getStatus().value());
            response.msg("响应异常，请联系管理员！");
        }
        if (ex instanceof FeignException) {
            response.code(HttpStatus.UNAUTHORIZED.value());
            response.msg("token失效，请重新登录！");
        }
        if (ex instanceof IllegalArgumentException) {
            response.msg("传参错误，请检查参数！");
        }
        if (ex instanceof NotFoundException) {
            response.code(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.msg("未查询到接口信息，请联系管理员！");
        }
        exceptionHandlerResult.set(response);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        HttpResult result = exceptionHandlerResult.get();
        return ServerResponse.status(HttpStatus.valueOf(result.getCode()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }

}