/*
 Copyright (c) 2012 Roman Truba

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package ru.truba.touchgallery.TouchView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import ru.truba.touchgallery.R;

public class UrlTouchImageView extends RelativeLayout {
    protected TouchImageView mImageView;

    protected Context mContext;

    public UrlTouchImageView(Context ctx)
    {
        super(ctx);
        mContext = ctx;
        init();
    }
    public UrlTouchImageView(Context ctx, AttributeSet attrs)
    {
        super(ctx, attrs);
        mContext = ctx;
        init();
    }
    public TouchImageView getImageView() { return mImageView; }

    @SuppressWarnings("deprecation")
    protected void init() {
        mImageView = new TouchImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mImageView.setLayoutParams(params);
        this.addView(mImageView);
    }

    public void setUrl(String imageUrl)
    {
        RequestOptions requestOptions = new RequestOptions();
        mImageView.setScaleType(ScaleType.CENTER);
        mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg_placeholder));
        Glide.with(getContext().getApplicationContext())
                .asBitmap()
                .load(imageUrl)
                .apply(requestOptions
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform())
                .into(new GlideTarget());
    }

    class GlideTarget extends SimpleTarget<Bitmap>{
        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            mImageView.setScaleType(ScaleType.MATRIX);
            mImageView.setImageBitmap(resource);
        }
    }
    
    public void setScaleType(ScaleType scaleType) {
        mImageView.setScaleType(scaleType);
    }

}
