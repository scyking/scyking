package com.scyking.common.base;

/**
 * 自定义运行时异常
 *
 * @author scyking
 **/
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 6045833912066796358L;

    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
