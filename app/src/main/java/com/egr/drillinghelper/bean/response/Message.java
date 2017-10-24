package com.egr.drillinghelper.bean.response;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * author lzd
 * date 2017/10/12 16:30
 * 类描述：
 */

public class Message {

    /**
     * id : kk3d4774-c695-ka4b-80d9-b0df8eb7f278
     * title : 系统通知
     * msg : 尊敬的用户，您的问题回复如下：液压系统xxxxxxxxxxx4
     * userId : f7ec427ce8fd4fefa2fa70b80f662262
     * isRead : false
     * isShow : true
     * code : TS0001
     * createtime : 2017-10-12 11:51:19
     * updatetime : 2017-10-12 11:51:19
     */
    @SerializedName(value = "id", alternate = {"messageId"})
    private String id;
    private String title;
    private String msg;
    private String userId;
    private boolean isRead;
    private boolean isShow;
    private String code;
    private String createtime;
    @SerializedName(value = "updatetime", alternate = {"date"})
    private String updatetime;

    public String getId() {
        return id;
    }

    public boolean isLoginConflict(){
        return TextUtils.equals(code,"TS0002");
    }

    public boolean isMessage(){
        return TextUtils.equals(code,"TS0001");
    }

    public boolean isServiceMsg(){
        return TextUtils.equals(code,"TS0003");
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
