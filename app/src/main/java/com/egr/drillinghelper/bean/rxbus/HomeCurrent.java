package com.egr.drillinghelper.bean.rxbus;

/**
 * author lzd
 * date 2017/10/23 15:45
 * 类描述：
 */

public class HomeCurrent {
    public HomeCurrent(int current) {
        this.current = current;
    }

    int current;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
