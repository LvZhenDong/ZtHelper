package com.egr.drillinghelper.api.error;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.exceptions.CompositeException;

/**
 * exception handle
 *
 * @author Ymmmsick
 * @date 2017-05-12 16:59:02
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                    ex.msg = "服务器异常，请稍后重试";
                    break;
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.msg = "网络连接错误,请检查网络";
                    break;

            }
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            ex.msg = "网络连接错误,请检查网络";
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponseThrowable(resultException, ERROR.SERVER_ERROR);
            ex.msg = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.msg = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            ex.msg = "连接失败";
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.msg = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.msg = "连接超时";
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.msg = "连接超时";
            return ex;
        } else if (e instanceof CompositeException) {
            for (Throwable throwable : ((CompositeException) e).getExceptions()) {
                if (throwable instanceof ResponseThrowable) {
                    ex = (ResponseThrowable) throwable;
                    return ex;
                }
            }
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.msg = "未知错误";
            return ex;
        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.msg = "未知错误";
            return ex;
        }
    }
}

