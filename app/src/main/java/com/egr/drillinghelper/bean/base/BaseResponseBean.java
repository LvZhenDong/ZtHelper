package com.egr.drillinghelper.bean.base;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ymmmsick on 5/12/17.
 */

public class BaseResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 1000L;

    protected boolean status;
    protected String code = "-1";//default
    @SerializedName("msg")
    protected String message = "";
    @SerializedName("body")
    protected T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public boolean isSuccess() {
        return status;
    }
}
