package com.egr.drillinghelper.common;

import android.content.Context;
import android.text.TextUtils;

import com.egr.drillinghelper.bean.response.UserInfo;

import cn.jpush.android.api.JPushInterface;

/**
 * author lzd
 * date 2017/10/16 18:03
 * 类描述：
 */

public class UserManager {

    private String sJPushId;
    private UserInfo userInfo=new UserInfo();

    private static class SingletonHolder{
        private static final UserManager instance=new UserManager();
    }

    public static UserManager getInstance(){
        return SingletonHolder.instance;
    }

    public void saveUserInfo(UserInfo data){
        if(data == null)return;
        this.userInfo=data;
        setTOKEN(data.getToken());
    }

    public UserInfo getUserInfo(){
        return userInfo;
    }

    public void updateUserPhoto(String photo){
        userInfo.setPhoto(photo);
    }

    public String getUserPhoto() {
        return userInfo.getPhoto();
    }

    public boolean isLogined(){
        return !TextUtils.isEmpty(userInfo.getId());
    }

    public void quit(){
        sJPushId=null;
        userInfo=new UserInfo();
        setTOKEN("");
    }
    public String getTOKEN() {
        if (TextUtils.isEmpty(userInfo.getToken())) {
            return MySharePreferencesManager.getInstance().getString("token", "");
        }
        return userInfo.getToken();
    }

    private void setTOKEN(String token) {
        MySharePreferencesManager.getInstance().putString("token", token);
    }

    public String getJPushId(Context context) {
        if (TextUtils.isEmpty(sJPushId)) {
            sJPushId = JPushInterface.getRegistrationID(context);
        }
        return sJPushId;
    }

    public void setJPushId(String id) {
        sJPushId = id;
    }

    public String getUserId() {
        return userInfo.getId();
    }
}
