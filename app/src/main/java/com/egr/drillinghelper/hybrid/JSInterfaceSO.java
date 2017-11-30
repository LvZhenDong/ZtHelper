package com.egr.drillinghelper.hybrid;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.egr.drillinghelper.ui.activity.GalleryActivity;

import java.util.ArrayList;

/**
 * author lzd
 * date 2017/10/10 16:42
 * 类描述：
 */

public class JSInterfaceSO {

    Activity mActivity;

    public JSInterfaceSO(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 点击图片回调方法
     */
    @JavascriptInterface
    public void openImage(String img) {
        ArrayList<String> imgs=new ArrayList<>();
        imgs.add(img);
        GalleryActivity.start(mActivity,imgs);
    }
}
