package com.egr.drillinghelper.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * author lzd
 * date 2017/10/13 11:15
 * 类描述：
 */

public class StoreMore {

    /**
     * id : 6fd1fb07dcf946468ba7e23e14a48af4
     * name : 商城地址
     * url : http://www.egrcn.com/product/list_13.aspx
     * createtime : 2017-10-12 16:14:06
     * updatetime : 2017-10-13 11:02:50
     */

    private String id;
    private String name;
    private String url;
    @SerializedName("createtime")
    private String createTime;
    @SerializedName("updatetime")
    private String updateTime;
    private String description;
    private String picture;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
