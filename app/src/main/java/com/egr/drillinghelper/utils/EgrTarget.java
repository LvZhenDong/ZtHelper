package com.egr.drillinghelper.utils;

import android.graphics.Bitmap;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.egr.drillinghelper.common.MyConstants;

/**
 * author lzd
 * date 2017/10/16 9:27
 * 类描述：通过Glide下载图片到本地
 */

public class EgrTarget extends SimpleTarget<Bitmap> {
    String name;

    public EgrTarget(String name) {
        super();
        this.name = name;
    }

    @Override
    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        FileUtils.saveImg(resource, MyConstants.PATH + name);
    }
}
