package com.egr.drillinghelper.bean.response;

import java.io.Serializable;

/**
 * author lzd
 * date 2017/10/10 17:25
 * 类描述：
 */

public class Feedback implements Serializable{

    /**
     * id : 8a31b7bc3e8e4bf4b8807edd711176da
     * createtime : 2017-10-10 16:41:23
     * updatetime : 2017-10-10 16:41:23
     * question : 阿斯顿发大水
     * photos : FEEDBACK/28294024444029.png,
     * answer : 阿斯顿发大水发
     * level : 1
     * answerTime : 2017-10-10 16:41:17
     * answerId : admin
     * status : 1
     */

    private String id;
    private String createtime;
    private String updatetime;
    private String question;
    private String photos;
    private String answer;
    private int level;
    private String answerTime;
    private String answerId;
    private int status;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
