package com.egr.drillinghelper.bean.response;

import java.util.List;

/**
 * author lzd
 * date 2017/10/10 17:48
 * 类描述：
 */

public class Reply {


    /**
     * records : [{"id":"c5981f6d67fa4b6c9748bf9550af4c57","createtime":"2017-10-10 17:46:58","updatetime":"2017-10-10 17:46:58","question":"Feedback question is ov","photos":"FEEDBACK/32228433887165.png,FEEDBACK/32228672872807.png,FEEDBACK/32228923896452.png,","status":0,"attachments":["http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228433887165.png","http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228672872807.png","http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228923896452.png"]}]
     * total : 1
     * size : 10
     * pages : 1
     * current : 1
     * searchCount : true
     * openSort : true
     * isAsc : true
     * offset : 0
     * limit : 2147483647
     */

    private int total;
    private int size;
    private int pages;
    private int current;
    private boolean searchCount;
    private boolean openSort;
    private boolean isAsc;
    private int offset;
    private int limit;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isOpenSort() {
        return openSort;
    }

    public void setOpenSort(boolean openSort) {
        this.openSort = openSort;
    }

    public boolean isIsAsc() {
        return isAsc;
    }

    public void setIsAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : c5981f6d67fa4b6c9748bf9550af4c57
         * createtime : 2017-10-10 17:46:58
         * updatetime : 2017-10-10 17:46:58
         * question : Feedback question is ov
         * photos : FEEDBACK/32228433887165.png,FEEDBACK/32228672872807.png,FEEDBACK/32228923896452.png,
         * status : 0
         * attachments : ["http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228433887165.png","http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228672872807.png","http://192.168.31.233:8083/egr/api/static/FEEDBACK/32228923896452.png"]
         */

        private String id;
        private String createtime;
        private String updatetime;
        private String question;
        private String photos;
        private String answer;
        private int status;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        private List<String> attachments;

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

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<String> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<String> attachments) {
            this.attachments = attachments;
        }
    }
}
