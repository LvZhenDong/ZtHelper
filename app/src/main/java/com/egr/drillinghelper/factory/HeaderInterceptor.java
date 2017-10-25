package com.egr.drillinghelper.factory;

import android.text.TextUtils;

import com.egr.drillinghelper.common.UserManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author 边凌
 * date 2017/6/28 11:34
 * 类描述：
 */

class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String token = UserManager.getInstance().getTOKEN();
        Request.Builder builder = original.newBuilder();
        if (!TextUtils.isEmpty(token)){
            builder.addHeader("token",token);
        }
        return chain.proceed(builder.build());
    }
}
