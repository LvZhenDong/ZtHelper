package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/10/16 14:06
 * 类描述：
 */

public class Share {

    /**
     * id : d7e5b0b0cf134f6ea62036d0f7e515f3
     * content : 轻便快速钻探整体服务提供商
     * qrcode : http://192.168.31.232:8083/egr/api/static/qrcode/1508130630968.png
     * createtime : 2017-10-16 10:47:04
     * updatetime : 2017-10-16 10:47:04
     */

    private String id;
    private String content;
    private String qrcode;
    private String createtime;
    private String updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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
