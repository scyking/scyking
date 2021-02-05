package com.scyking.common.base;

/**
 * json相关异常
 *
 * @author scyking
 **/
public class JsonException extends BaseException {
    private static final long serialVersionUID = -7731313074165603811L;

    public JsonException() {
        super();
    }

    public JsonException(String msg) {
        super(msg);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
