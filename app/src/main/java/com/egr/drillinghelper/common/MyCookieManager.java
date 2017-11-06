package com.egr.drillinghelper.common;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.egr.drillinghelper.utils.L;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;

public class MyCookieManager {

    /**
     * 保存cookie
     *
     * @param cookies
     */
    public static void saveCookie(List<Cookie> cookies) {
        MySharePreferencesManager.getInstance().putString(MyConstants.TAG_COOKIE, new Gson().toJson
                (cookies));
    }

    /**
     * 读取cookie
     *
     * @return
     */
    public static List<Cookie> readCookie() {
        String cookieStr = MySharePreferencesManager.getInstance().getString(MyConstants.TAG_COOKIE, null);
        if (cookieStr != null) {
            List<Cookie> cookies = new Gson().fromJson(cookieStr, new TypeToken<List<Cookie>>() {
            }.getType());
            return cookies;
        }
        return new ArrayList<Cookie>();
    }

    /**
     * 同步cookie for H5
     *
     * @param context
     * @param url
     */
    public static void syncCookie(Context context, String url) {
        String cookieStr = MySharePreferencesManager.getInstance().getString(MyConstants.TAG_COOKIE, null);
        if (cookieStr == null)
            return;
        List<Cookie> cookies = new Gson().fromJson(cookieStr, new TypeToken<List<Cookie>>() {
        }.getType());
        StringBuffer sb = new StringBuffer();
        for (Cookie cookie : cookies) {
            sb.append(cookie.name() + "=" + cookie.value() + ";");
        }
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        for (String cookie : sb.toString().split(";")) {
            cookieManager.setCookie(url, cookie);
        }
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 清除SP Cookie
     */
    public static void cleanCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
        boolean result = MySharePreferencesManager.getInstance().remove(MyConstants.TAG_COOKIE);
        if (!result)
            L.e("clean cookie fail");
    }
}
