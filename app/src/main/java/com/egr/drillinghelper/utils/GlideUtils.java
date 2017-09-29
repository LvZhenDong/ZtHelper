package com.egr.drillinghelper.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * author lzd
 * date 2017/9/29 22:21
 * 类描述：
 */

public class GlideUtils {

    public static void load(Context context, String url, ImageView view){
        Glide.with(context)
                .load(url)
                .into(view);
    }
}
