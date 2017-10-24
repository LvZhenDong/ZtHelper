package com.egr.drillinghelper.common;

import android.content.Context;
import android.text.TextUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * author lzd
 * date 2017/10/16 18:03
 * 类描述：
 */

public class UserManager {

    private static String sJPushId;
    private static String userId;
    private static String sToken;
    private static String userPhoto;

    public static String getUserPhoto() {
        return userPhoto;
    }

    public static void setUserPhoto(String userPhoto) {
        UserManager.userPhoto = userPhoto;
    }

    public static boolean isLogined(){
        return !TextUtils.isEmpty(userId);
    }

    public static void quit(){
        sJPushId=null;
        userId=null;
        setTOKEN("");
    }
    public static String getTOKEN() {
        if (TextUtils.isEmpty(sToken)) {
            sToken = MySharePreferencesManager.getInstance().getString("token", "");
        }
        return sToken;
    }

    public static void setTOKEN(String token) {
        sToken = token;
        MySharePreferencesManager.getInstance().putString("token", token);
    }

    public static String getsJPushId(Context context) {
        if (TextUtils.isEmpty(sJPushId)) {
            sJPushId = JPushInterface.getRegistrationID(context);
        }
        return sJPushId;
    }

    public static void setJPushId(String id) {
        sJPushId = id;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserManager.userId = userId;
    }
}
