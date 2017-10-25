package com.egr.drillinghelper.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * author lzd
 * date 2017/10/24 11:30
 * 类描述：
 */

public class ServiceMsg {

    public static ServiceMsg createSendText(String time, String text){
        ServiceMsg msg=new ServiceMsg();
        msg.setSend(true);
        msg.setCreateTime(time);
        msg.setMsg(text);
        msg.setSendState(2);
        return msg;
    }

    public static ServiceMsg createSendImg(String time, String img){
        ServiceMsg msg=new ServiceMsg();
        msg.setSend(true);
        msg.setCreateTime(time);
        msg.setSendState(2);
        List<String> imgs = new ArrayList<>();
        imgs.add(img);
        msg.setPictureList(imgs);

        return msg;
    }

    public static ServiceMsg createRecText(Message message){
        ServiceMsg rec=new ServiceMsg();
        rec.setSend(false);
        rec.setCreateTime(message.getUpdatetime());
        rec.setMsg(message.getMsg());

        return rec;
    }

    /**
     * id : 34abadf8f8c643798294e593b3eec3a6
     * createtime : 2017-10-24 16:48:44
     * supportId : 97e76ab0920b48e4a51625a9773e2b4e
     * userId : f7ec427ce8fd4fefa2fa70b80f662262
     * userName : lze
     * isAdmin : 0
     * pictureList : ["http://192.168.31.232:8083/egr/api/static/servicesupport/29159668291111.jpg"]
     */

    private int sendState = 0;
    private String id;
    @SerializedName("createtime")
    private String createTime;
    private String supportId;
    private String userId;
    private String userName;
    private String msg;
    private int isAdmin;
    private List<String> pictureList;

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public boolean isSend() {
        return isAdmin==0;
    }

    public void setSend(boolean send) {
        this.isAdmin=send?0:1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSupportId() {
        return supportId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }
}
