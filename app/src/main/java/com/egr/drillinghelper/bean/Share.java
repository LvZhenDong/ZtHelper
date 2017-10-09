package com.egr.drillinghelper.bean;

/**
 * author lzd
 * date 2017/10/9 14:38
 * 类描述：
 */

public class Share {

    public Share(int iconId, int name) {
        this.iconId = iconId;
        this.name = name;
    }

    int iconId;
    int name;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
