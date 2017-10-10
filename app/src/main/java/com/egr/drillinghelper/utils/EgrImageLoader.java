package com.egr.drillinghelper.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;

/**
 * author lzd
 * date 2017/9/29 16:02
 * 类描述：ImagePicker所需要的loader
 */

public class EgrImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path,
                             ImageView imageView, int width, int height) {
        GlideUtils.loadLocalImg(path, imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path,
                                    ImageView imageView, int width, int height) {
        GlideUtils.loadLocalImg(path, imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
