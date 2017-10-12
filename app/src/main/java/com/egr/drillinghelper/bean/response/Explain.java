package com.egr.drillinghelper.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author lzd
 * date 2017/10/11 16:23
 * 类描述：
 */

public class Explain {
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
    @SerializedName(value = "title", alternate = {"bookTitle"})
    private String title;
    private String description;
    @SerializedName(value = "photo", alternate = {"bookPhoto"})
    private String photo;
    private List<ExplainCatalog> catalogs;
    private List<KnowCatalog> knows;

    public List<KnowCatalog> getKnows() {
        return knows;
    }

    public void setKnows(List<KnowCatalog> knows) {
        this.knows = knows;
    }

    public List<ExplainCatalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<ExplainCatalog> catalogs) {
        this.catalogs = catalogs;
    }

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
