package com.egr.drillinghelper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * author 万强
 * date 16/5/11 下午4:37
 * desc SharedPreferences本地保存
 */
public class SharePreHelper {

    /**
     * 用户名
     */
    public static final String LOGIN_NAME = "_name";

    /**
     * 密码
     */
    public static final String LOGIN_PSW = "_psw";


    private SharedPreferences sp;

    private Editor edit;

    private SharePreHelper() {
    }

    private static SharePreHelper helper;

    public static SharePreHelper getIns() {
        if (helper == null) {
            helper = new SharePreHelper();
        }
        return helper;
    }

    /**
     * 初始化
     *
     * @param context
     * @param name
     */
    public void initialize(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        edit = sp.edit();
    }

    /**
     * 设置String数据
     *
     * @param key
     * @param data
     */
    public void setTextData(String key, String data) {

        edit.putString(key, data);
        edit.commit();
    }

    /**
     * 获取String型数据
     *
     * @param key
     * @return
     */
    public String getTextData(String key) {
        return sp.getString(key, "");
    }

    /**
     * 获取String型数据，需传默认值
     *
     * @param key
     * @param defaultData
     * @return
     */
    public String getTextData(String key, String defaultData) {
        return sp.getString(key, defaultData);
    }

    /**
     * 设置Boolean数据
     *
     * @param key
     * @param data
     */
    public void setBooleanData(String key, boolean data) {
        edit.putBoolean(key, data);
        edit.commit();
    }

    /**
     * 获取Boolean型数据
     *
     * @param key
     * @return
     */
    public boolean getBooleanData(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * 获取Boolean型数据，需传默认值
     *
     * @param key
     * @param defaultData
     * @return
     */
    public boolean getBooleanData(String key, boolean defaultData) {
        return sp.getBoolean(key, defaultData);
    }


    public long getLongData(String key, long defaultData) {
        return sp.getLong(key, defaultData);
    }

    public long getLongData(String key) {
        return sp.getLong(key, 0);
    }


    public void setLongData(String key, long data) {
        edit.putLong(key, data);
        edit.commit();
    }


}
