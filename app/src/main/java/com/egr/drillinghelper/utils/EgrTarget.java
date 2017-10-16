package com.egr.drillinghelper.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.egr.drillinghelper.common.MyConstants;

/**
 * author lzd
 * date 2017/10/16 9:27
 * 类描述：
 */

public class EgrTarget extends SimpleTarget<Drawable> {
    String name;

    public EgrTarget(String name) {
        super();
        this.name = name;
    }

    @Override
    public void onResourceReady(Drawable resource, Transition transition) {

        Bitmap bitmap= BitmapUtils.drawableToBitmap(resource);
        FileUtils.saveImg(bitmap,MyConstants.PATH+name);
    }
}
