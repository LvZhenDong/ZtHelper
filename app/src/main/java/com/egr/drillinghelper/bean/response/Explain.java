package com.egr.drillinghelper.bean.response;

import java.util.List;

/**
 * author lzd
 * date 2017/9/29 15:28
 * 类描述：
 */

public class Explain {

    /**
     * records : [{"id":"4dd2ef7754054944be846bfd882eaa90","createTime":"2017-09-29 12:01:53","updateTime":"2017-09-29 12:01:53","title":"这是说明书","description":"这是说明书1","photo":"user/11390913671957.jpg"},{"id":"a78d55d4f1e8476a814925799d15a6f6","createTime":"2017-09-29 15:35:23","updateTime":"2017-09-29 15:35:23","title":"说明书2","description":"说明书2","photo":"user/24199379975408."}]
     * total : 2
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
}
