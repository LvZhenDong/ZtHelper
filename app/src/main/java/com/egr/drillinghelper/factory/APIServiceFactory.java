package com.egr.drillinghelper.factory;

import android.text.TextUtils;

import com.egr.drillinghelper.BuildConfig;
import com.egr.drillinghelper.common.MySharePreferencesManager;
import com.egr.drillinghelper.utils.L;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIServiceFactory {

    private final static long DEFAULT_TIMEOUT = 10;
    private final Gson mGsonDateFormat;

    private APIServiceFactory() {
        mGsonDateFormat = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    private static class SingletonHolder {
        private static final APIServiceFactory INSTANCE = new APIServiceFactory();
    }

    public static APIServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * create a service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass) {
        String baseUrl = BuildConfig.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    private OkHttpClient getOkHttpClient() {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
//        File httpCacheDirectory = new File(FileUtils.getCacheDir(MyApplication.getInstance()), "OkHttpCache");
//        httpClientBuilder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));
        if (BuildConfig.DEBUG) {
//            LoggingInterceptor interceptor = new LoggingInterceptor.Builder()
//                    .loggable(BuildConfig.DEBUG)
//                    .setLevel(Level.BASIC)
//                    .log(Platform.INFO)
//                    .request("EGR-Request")
//                    .response("EGR-Response")
//                    .build();
//            httpClientBuilder.addInterceptor(interceptor);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {


                    L.printJson(message);

                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor);
        }
        //add cookie manage
//        httpClientBuilder.cookieJar(new CookieJar() {
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                MyCookieManager.saveCookie(cookies);
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                return MyCookieManager.readCookie();
//            }
//        });
        httpClientBuilder.addInterceptor(new HeaderInterceptor());
        return httpClientBuilder.build();
    }

    public static String getTOKEN() {
        if (TextUtils.isEmpty(sToken)) {
            sToken = MySharePreferencesManager.getInstance().getString("token","");
        }
        return sToken;
    }

    private static String sToken;

    public static void setTOKEN(String token) {
        sToken = token;
        MySharePreferencesManager.getInstance().putString("token",token);
    }
}