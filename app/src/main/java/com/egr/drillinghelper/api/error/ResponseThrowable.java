package com.egr.drillinghelper.api.error;

public class ResponseThrowable extends Exception {
    public int code;
    public String msg;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    /**
     * 获取统一修改过的Message
     *
     * @return
     */
    public String getLMessage() {
        return msg;
    }
}