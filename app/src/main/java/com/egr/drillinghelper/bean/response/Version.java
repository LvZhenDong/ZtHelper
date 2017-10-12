package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/10/12 15:41
 * 类描述：
 */

public class Version {

    /**
     * id : d4ce10e9-dc87-4431-b823-4e08d59aa1a0
     * name : 英格尔钻探助手
     * code : 1
     * description : 新版本钻探助手
     * url : https://www.baidu.com/
     * enable : true
     * clientType : 0
     * createtime : 2017-05-16 09:07:54
     * updatetime : 2017-05-16 09:07:44
     */

    private String id;
    private String name;
    private int code;
    private String description;
    private String url;
    private boolean enable;
    private int clientType;
    private String createtime;
    private String updatetime;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
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
}
