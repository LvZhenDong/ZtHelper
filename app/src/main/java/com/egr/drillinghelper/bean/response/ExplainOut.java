package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/10/11 16:23
 * 类描述：
 */

public class ExplainOut {
    /**
     * id : 4dd2ef7754054944be846bfd882eaa90
     * createTime : 2017-09-29 12:01:53
     * updateTime : 2017-09-29 12:01:53
     * title : 这是说明书
     * description : 这是说明书1
     * photo : user/11390913671957.jpg
     */

    private String id;
    private String createTime;
    private String updateTime;
    private String title;
    private String description;
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
