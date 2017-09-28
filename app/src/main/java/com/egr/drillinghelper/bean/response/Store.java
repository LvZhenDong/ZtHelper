package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/9/28 17:51
 * 类描述：
 */

public class Store {

    /**
     * id : 12q6c5acaa8841b381bb4a111310a166
     * name : 薄壁钻头
     * information : 英格尔薄壁系列钻头
     * url : http://www.egrcn.com/product/list_13.aspx
     * enable : true
     * createtime : 2017-09-26 16:23:33
     * updatetime : 2017-09-26 16:23:33
     */

    private String id;
    private String name;
    private String information;
    private String url;
    private boolean enable;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
