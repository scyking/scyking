package com.scyking.common.base;

/**
 * @author scyking
 **/
public class ExportFileException extends BaseException {
    private static final long serialVersionUID = -7910644337046024363L;

    public ExportFileException() {
        super();
    }

    public ExportFileException(String msg) {
        super(msg);
    }

    public ExportFileException(Throwable cause) {
        super(cause);
    }

    public ExportFileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
