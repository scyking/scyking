package com.scyking.common.responses;

import lombok.Data;

/**
 * @author scyking
 * @description
 * @createTime 2020/12/29 16:50
 **/
@Data
public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T data;
}
