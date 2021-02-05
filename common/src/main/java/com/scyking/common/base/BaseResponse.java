package com.scyking.common.base;

import lombok.Getter;

/**
 * http 响应结果
 * <p>链式返回结果</p>
 *
 * @author scyking
 **/
@Getter
public class BaseResponse<T> {
    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    private int code;
    private String msg;
    private T data;

    private BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse success() {
        return new BaseResponse<>(BaseResponse.SUCCESS, "操作成功！", null);
    }

    public static BaseResponse error() {
        return new BaseResponse<>(BaseResponse.ERROR, "操作失败！", null);
    }

    public BaseResponse msg(String message) {
        this.msg = message;
        return this;
    }

    public BaseResponse data(T data) {
        this.data = data;
        return this;
    }

    public BaseResponse code(int code) {
        this.code = code;
        return this;
    }
}
