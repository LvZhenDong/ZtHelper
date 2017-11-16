package com.egr.drillinghelper.ui.widgets;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * 方块的指示器
 *
 * @author LvZhenDong
 *         created on 2017/11/16 14:58
 */
public class TabEntity implements CustomTabEntity {
    public TabEntity(String title) {
        this.title = title;
    }



    String title;

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
