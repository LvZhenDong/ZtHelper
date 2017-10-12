package com.egr.drillinghelper.bean.response;

import java.util.List;

/**
 * author lzd
 * date 2017/9/29 19:08
 * 类描述：
 */

public class ExplainCatalog {
    String id;
    String title;
    String orderNumber;
    String parentId;
    int version;
    String explainId;
    String articleId;
    String url;
    List<ExplainCatalog> childs;

    Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    int deep;

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getExplainId() {
        return explainId;
    }

    public void setExplainId(String explainId) {
        this.explainId = explainId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ExplainCatalog> getChilds() {
        return childs;
    }

    public void setChilds(List<ExplainCatalog> childs) {
        this.childs = childs;
    }
}
