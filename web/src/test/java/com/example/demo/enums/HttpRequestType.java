package com.example.demo.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * http 请求类型
 */
public enum HttpRequestType {
    GET(0, "GET"), POST(1, "POST");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String value;

    HttpRequestType(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
