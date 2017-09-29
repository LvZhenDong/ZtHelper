package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/9/29 20:47
 * 类描述：
 */

public class Article {

    /**
     * id : f56aea01a6c74d4388130bdfd2bbdec6
     * createtime : 2017-09-29 20:23:58
     * updatetime : 2017-09-29 20:23:58
     * catalogId : 2e3ca0e8438f4cd589752949e5b05fb5
     * title : 阿什顿发斯蒂芬
     * content : <p>请添加您的文章内容...</p><h1>Java</h1><p>&nbsp;<img src="https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=f50cf73fd843ad4ba62e41c6ba393d92/0df431adcbef76096709930527dda3cc7cd99e2d.jpg"></p><h2>（计算机编程语言）</h2><div label-module="para"><a target="_blank" href="https://baike.baidu.com/item/Java/85979" data-lemmaid="85979">Java</a>是一门<a target="_blank" href="https://baike.baidu.com/item/%E9%9D%A2%E5%90%91%E5%AF%B9%E8%B1%A1">面向对象</a>编程语言，不仅吸收了<a target="_blank" href="https://baike.baidu.com/item/C%2B%2B">C++</a>语言的各种优点，还摒弃了C++里难以理解的<a target="_blank" href="https://baike.baidu.com/item/%E5%A4%9A%E7%BB%A7%E6%89%BF">多继承</a>、<a target="_blank" href="https://baike.baidu.com/item/%E6%8C%87%E9%92%88">指针</a>等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程<sup>[1]</sup><a name="ref_[1]_12654100">&nbsp;</a>&nbsp;。</div><div label-module="para">Java具有简单性、面向对象、<a target="_blank" href="https://baike.baidu.com/item/%E5%88%86%E5%B8%83%E5%BC%8F/19276232" data-lemmaid="19276232">分布式</a>、<a target="_blank" href="https://baike.baidu.com/item/%E5%81%A5%E5%A3%AE%E6%80%A7">健壮性</a>、<a target="_blank" href="https://baike.baidu.com/item/%E5%AE%89%E5%85%A8%E6%80%A7">安全性</a>、平台独立与可移植性、<a target="_blank" href="https://baike.baidu.com/item/%E5%A4%9A%E7%BA%BF%E7%A8%8B">多线程</a>、动态性等特点<sup>[2]</sup><a name="ref_[2]_12654100">&nbsp;</a>&nbsp;。Java可以编写<a target="_blank" href="https://baike.baidu.com/item/%E6%A1%8C%E9%9D%A2%E5%BA%94%E7%94%A8%E7%A8%8B%E5%BA%8F">桌面应用程序</a>、<a target="_blank" href="https://baike.baidu.com/item/Web%E5%BA%94%E7%94%A8%E7%A8%8B%E5%BA%8F">Web应用程序</a>、<a target="_blank" href="https://baike.baidu.com/item/%E5%88%86%E5%B8%83%E5%BC%8F%E7%B3%BB%E7%BB%9F">分布式系统</a>和<a target="_blank" href="https://baike.baidu.com/item/%E5%B5%8C%E5%85%A5%E5%BC%8F%E7%B3%BB%E7%BB%9F">嵌入式系统</a>应用程序等<sup>[3]</sup><a name="ref_[3]_12654100">&nbsp;</a>&nbsp;。</div>
     * version : 1
     */

    private String id;
    private String createtime;
    private String updatetime;
    private String catalogId;
    private String title;
    private String content;
    private int version;

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

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
