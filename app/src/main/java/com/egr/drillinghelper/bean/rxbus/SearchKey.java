package com.egr.drillinghelper.bean.rxbus;

/**
 * author lzd
 * date 2017/10/23 15:45
 * 类描述：
 */

public class SearchKey {
    public SearchKey(String key, int type) {
        this.key = key;
        this.type = type;
    }

    String key;
    int type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
