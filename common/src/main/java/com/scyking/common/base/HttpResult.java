package com.scyking.common.base;

import lombok.Getter;

import java.io.Serializable;

/**
 * http 响应结果
 * <p>链式返回结果</p>
 *
 * @author scyking
 **/
@Getter
public class HttpResult<T> implements Serializable {
    private static final long serialVersionUID = 2555861069016713083L;

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;


    private int code;
    private String msg;
    private T data;

    private HttpResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> HttpResult<T> success() {
        return new HttpResult<>(HttpResult.SUCCESS, "操作成功！", null);
    }

    public static <T> HttpResult<T> error() {
        return new HttpResult<>(HttpResult.ERROR, "操作失败！", null);
    }

    public HttpResult msg(String message) {
        this.msg = message;
        return this;
    }

    public HttpResult data(T data) {
        this.data = data;
        return this;
    }

    public HttpResult code(int code) {
        this.code = code;
        return this;
    }
}
