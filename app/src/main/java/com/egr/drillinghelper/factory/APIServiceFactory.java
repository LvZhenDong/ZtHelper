package com.egr.drillinghelper.factory;

import android.text.TextUtils;

import com.egr.drillinghelper.BuildConfig;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.app.MyApplication;
import com.egr.drillinghelper.utils.FileUtils;
import com.egr.drillinghelper.utils.L;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIServiceFactory {

    private final static long DEFAULT_TIMEOUT = 20;
    static String baseUrl;
    private final Gson mGsonDateFormat;
    NetApi netApi;

    private APIServiceFactory() {
        mGsonDateFormat = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    public static APIServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String url) {
        baseUrl = "http://" + url + "/api/";
    }

    /**
     * create a service
     *
     * @return
     */
    public NetApi createService() {

        if (netApi == null) {
            if (TextUtils.isEmpty(baseUrl))
                baseUrl = BuildConfig.BASE_URL;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            netApi = retrofit.create(NetApi.class);
            return netApi;
        } else {
            return netApi;
        }

    }

    private OkHttpClient getOkHttpClient() {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
        File httpCacheDirectory = new File(FileUtils.getCacheDir(MyApplication.getInstance()), "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    L.printJson(message);
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor);
        }

        httpClientBuilder.addInterceptor(new HeaderInterceptor());
        return httpClientBuilder.build();
    }

    private static class SingletonHolder {
        private static final APIServiceFactory INSTANCE = new APIServiceFactory();
    }
}