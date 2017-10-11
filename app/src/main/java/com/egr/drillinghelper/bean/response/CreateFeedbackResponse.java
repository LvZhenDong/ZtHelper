package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/10/11 11:04
 * 类描述：
 */

public class CreateFeedbackResponse {

    /**
     * id : c5981f6d67fa4b6c9748bf9550af4c57
     * createtime : 2017-10-10 17:46:58
     * updatetime : 2017-10-10 18:16:39
     * question : Feedback question is ov
     * photos : FEEDBACK/32228433887165.png,FEEDBACK/32228672872807.png,FEEDBACK/32228923896452.png,
     * answer : <p><strong>埃里克森觉得会计分录速度快</strong></p><p><imgsrc="/static/product/34006563359849.png"alt=""/></p>
     * answerTime : 2017-10-10 18:16:39
     * answerId : e5c2864b21a8487382da1177005cb911
     */

    private String id;
    private String createtime;
    private String updatetime;
    private String question;
    private String photos;
    private String answer;
    private String answerTime;
    private String answerId;

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
}
