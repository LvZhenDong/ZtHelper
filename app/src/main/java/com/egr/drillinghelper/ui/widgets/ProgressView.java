package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.egr.drillinghelper.R;

/**
 * author lzd
 * date 2017/10/26 16:49
 * 类描述：
 */

public class ProgressView extends android.support.v7.widget.AppCompatImageView {

    public ProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAnimation(attrs);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAnimation(attrs);
    }

    public ProgressView(Context context) {
        super(context);
    }

    private void setAnimation(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressView);
        int frameCount = a.getInt(R.styleable.ProgressView_frameCount, 12);
        int duration = a.getInt(R.styleable.ProgressView_duration, 1000);
        a.recycle();

        setAnimation(frameCount, duration);
    }

    public void setAnimation(final int frameCount, final int duration) {
        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.progress_anim);
        a.setDuration(duration);
        a.setInterpolator(new Interpolator() {

            @Override
            public float getInterpolation(float input) {
                return (float)Math.floor(input*frameCount)/frameCount;
            }
        });
        startAnimation(a);
    }
}
