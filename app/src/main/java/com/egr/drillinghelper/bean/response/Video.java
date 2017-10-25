package com.egr.drillinghelper.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * author lzd
 * date 2017/10/25 15:17
 * 类描述：
 */

public class Video {

    /**
     * id : 407c04c1a8c64d329990aad48d1ea59d
     * createtime : 2017-10-25 15:03:02
     * updatetime : 2017-10-25 15:03:02
     * desc : 说明书目录视频
     * type : 1
     * url : http://fanyi.baidu.com/?aldtype=16047#zh/en/%E5%85%B3%E8%81%94
     * relationId : 0cdada4b94554759ae9b3479e1ee02bb
     */

    private String id;
    @SerializedName("createtime")
    private String createTime;
    @SerializedName("updatetime")
    private String updateTime;
    private String desc;
    private int type;
    private String url;
    private String relationId;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}
