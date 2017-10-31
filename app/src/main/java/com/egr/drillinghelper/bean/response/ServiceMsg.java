package com.egr.drillinghelper.bean.response;

import com.egr.drillinghelper.utils.CollectionUtil;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * author lzd
 * date 2017/10/24 11:30
 * 类描述：
 */

public class ServiceMsg {

    public static final int TYPE_SEND_TEXT = 0;
    public static final int TYPE_SEND_IMG = 1;
    public static final int TYPE_REC_TEXT = 2;
    public static final int TYPE_REC_IMG = 3;
    public static final int TYPE_REC_MATCH = 4;
    public static final int TYPE_REC_MATCH_EMPTY = 5;
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
    private int status;
    private int isAdmin;    //0:用户发生，1：服务人员发送
    private List<String> pictureList;

    public ServiceMsg createSendText(String time, String text) {
        ServiceMsg msg = new ServiceMsg();
        msg.setSend(true);
        msg.setCreateTime(time);
        ChatBean chatBean=new ChatBean();
        chatBean.setMessage(text);
        msg.setMsg(new Gson().toJson(chatBean));
        msg.setSendState(2);
        return msg;
    }

    public static ServiceMsg createSendImg(String time, String img) {
        ServiceMsg msg = new ServiceMsg();
        msg.setSend(true);
        msg.setCreateTime(time);
        msg.setSendState(2);
        List<String> imgs = new ArrayList<>();
        imgs.add(img);
        msg.setPictureList(imgs);

        return msg;
    }

    public static ServiceMsg createRecText(Message message) {
        ServiceMsg rec = new ServiceMsg();
        rec.setSend(false);
        rec.setCreateTime(message.getUpdatetime());
        rec.setMsg(message.getMsg());

        return rec;
    }

    public int getType() {
        int showType = 0;
        switch (isAdmin) {
            case 0:
                if (CollectionUtil.isListEmpty(pictureList))
                    showType = TYPE_SEND_TEXT;
                else
                    showType = TYPE_SEND_IMG;
                break;
            case 1:
                if (!CollectionUtil.isListEmpty(pictureList))
                    showType = TYPE_REC_IMG;
                else {
                    MessageBean bean = new Gson().fromJson(msg, MessageBean.class);
                    switch (bean.type) {
                        case 0:
                            showType = TYPE_REC_TEXT;
                            break;
                        case 1:
                            showType = TYPE_REC_MATCH;
                            break;
                        case 2:
                            showType = TYPE_REC_MATCH_EMPTY;
                            break;
                    }
                }

                break;
        }
        return showType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public void setSend(boolean send) {
        this.isAdmin = send ? 0 : 1;
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

    public class MessageBean {
        int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public class KnowBean extends MessageBean{
        List<KnowCatalog> message;

        public List<KnowCatalog> getMessage() {
            return message;
        }

        public void setMessage(List<KnowCatalog> message) {
            this.message = message;
        }
    }

    public class ChatBean extends MessageBean{
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
