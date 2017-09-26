package com.egr.drillinghelper.bean.base;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ymmmsick on 5/12/17.
 */

public class BaseResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 1000L;
    @SerializedName("api_code")
    protected String code = "-1";//default
    @SerializedName("api_message")
    protected String message;
    @SerializedName("api_data")
    protected T data;

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
        return code.equals("0");
    }
}
