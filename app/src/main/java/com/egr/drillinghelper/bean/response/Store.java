package com.egr.drillinghelper.bean.response;

import java.util.List;

/**
 * author lzd
 * date 2017/9/28 17:51
 * 类描述：
 */

public class Store {


    /**
     * records : [{"id":"12q6c5acaa8841b381bb4a111310a166","name":"薄壁钻头","information":"英格尔薄壁系列钻头","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"12q6c5acaa8841b381bb4a111311a166","name":"液压岩芯钻机","information":"铝合金全液压岩芯钻机","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"12q6c5acaa8841b38cbb4a111310a166","name":"用途柱塞泵","information":"英格尔多用途柱塞泵","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"31q6c5acaa8841b18cbb4a111310a166","name":"钻具总成","information":"英格尔薄壁系列钻头总成","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"31q6c5acaa8841b38cbb4a111310a166","name":"高压抽水机","information":"英格尔EW系列高压抽水机","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"32q6c5acaa8841b38cbb4a111310a166","name":"多功能泥浆泵","information":"英格尔多功能泥浆泵，搅拌与泵送泥浆一台搞定","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"3rq6c5acaa8841b38cbb4a111310a166","name":"英格尔薄壁系列钻具","information":"英格尔薄壁系列钻具扩孔器","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"41q6c5acaa8841b18cbb4a111310a166","name":"丝扣油","information":"英格尔钻具油脂","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"8976c1acaa8841b38cb14a1113101166","name":"钻杆油","information":"英格尔钻具油脂","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"8976c1acaa8841b38cb14a111310a166","name":"通用型配方泥浆","information":"地勘配方泥浆","url":"http://www.egrcn.com/product/list_13.aspx","enable":true,"createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"}]
     * total : 16
     * size : 10
     * pages : 2
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
        private String picture;

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
}
