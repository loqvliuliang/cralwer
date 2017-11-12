package com.example.demo.exception;

import java.util.Arrays;

/**
 * 业务异常
 *
 * @author elvis.xu
 * @since 2016-06-08 13:25
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    protected String code;
    protected Object[] args;
    protected String msg;

    public BizException(String code) {
        this(code, null, null, null);
    }

    public BizException(String code, Object[] args) {
        this(code, args, null, null);
    }

    public BizException(String code, String message) {
        this(code, null, message, null);
    }

    public BizException(String code, Object[] args, String message) {
        this(code, args, message, null);
    }

    public BizException(String code, Throwable cause) {
        this(code, null, null, cause);
    }

    public BizException(String code, Object[] args, Throwable cause) {
        this(code, args, null, cause);
    }

    public BizException(String code, String message, Throwable cause) {
        this(code, null, message, cause);
    }

    public BizException(String code, Object[] args, String message, Throwable cause) {
        super(message,cause);
        this.code = code;
        this.args = args;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getSimpleName());
        buf.append("[code='" + getCode() + "'");
        buf.append(", args=" + Arrays.toString(args));
        buf.append(", msg='" + msg + "']");
        String message = getLocalizedMessage();
        buf.append((message != null) ? ": " + message : "");
        StackTraceElement[] traces = getStackTrace();
        buf.append(traces.length == 0 ? "" : ": at " + traces[0]);
        return buf.toString();
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
