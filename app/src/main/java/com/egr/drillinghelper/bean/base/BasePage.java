package com.egr.drillinghelper.bean.base;

import java.util.List;

/**
 * author lzd
 * date 2017/10/11 14:20
 * 类描述：
 */

public class BasePage<T> {

    /**
     * records : [{"id":"7cfedbee1b25491f957aee7570a6f54e","createtime":"2017-09-30 23:35:04","updatetime":"2017-09-30 14:26:09","title":"EP200便携式全液压钻机","description":"EP200便携式全液压钻机使用说明书","photo":"user/19677510966819.png","version":3}]
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
    private List<T> records;

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

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }


}
