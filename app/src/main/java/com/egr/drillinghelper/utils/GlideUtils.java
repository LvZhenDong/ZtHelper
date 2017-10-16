package com.egr.drillinghelper.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.egr.drillinghelper.R;

/**
 * author lzd
 * date 2017/9/29 22:21
 * 类描述：
 */

public class GlideUtils {

    public static void load(String url, ImageView view) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(requestOptions.placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view);
    }

    public static void load(int resId, ImageView view) {
        RequestOptions requestOptions = new RequestOptions();

        Glide.with(view.getContext().getApplicationContext())
                .load(resId)
                .apply(requestOptions.placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view);
    }

    public static void loadCircleImg(String url, ImageView view) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(requestOptions
                        .placeholder(R.drawable.ic_head)
                        .error(R.drawable.ic_head)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view);
    }

    public static void loadLocalImg(int resId, ImageView view) {
        Glide.with(view.getContext())
                .load(resId)
                .into(view);
    }

    public static void loadLocalImg(String path, ImageView view) {
        Glide.with(view.getContext())
                .load(path)
                .into(view);
    }

    public static void perLoadImg(Context context,String url){
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(context)
                .load(url)
                .apply(requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .preload();
    }
}
