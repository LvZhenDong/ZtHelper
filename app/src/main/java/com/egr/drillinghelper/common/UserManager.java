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

    public static String getsJPushId(Context context) {
        if (TextUtils.isEmpty(sJPushId)) {
            sJPushId = JPushInterface.getRegistrationID(context);
        }
        return sJPushId;
    }

    public static void setJPushId(String id){
        sJPushId=id;
    }
}
