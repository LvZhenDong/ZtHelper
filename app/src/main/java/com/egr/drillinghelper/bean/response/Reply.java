package com.egr.drillinghelper.bean.response;

import java.util.ArrayList;

/**
 * author lzd
 * date 2017/10/10 17:48
 * 类描述：
 */

public class Reply {


    /**
     * id : c5981f6d67fa4b6c9748bf9550af4c57
     * createtime : 2017-10-10 17:46:58
     * updatetime : 2017-10-10 17:46:58
     * question : Feedback question is ov
     * photos : FEEDBACK/32228433887165.png,FEEDBACK/32228672872807.png,FEEDBACK/32228923896452.png,
     * status : 0
     * attachments : ["http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228433887165.png","http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228672872807.png","http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228923896452.png"]
     */

    private String id;
    private String createtime;
    private String updatetime;
    private String question;
    private String photos;
    private String answer;
    private int status;
    private ArrayList<String> attachments;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<String> attachments) {
        this.attachments = attachments;
    }

}
