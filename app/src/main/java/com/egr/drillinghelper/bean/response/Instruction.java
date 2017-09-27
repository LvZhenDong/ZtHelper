package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/9/27 15:36
 * 类描述：使用说明
 */

public class Instruction {
    int imgId;
    String title;
    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
