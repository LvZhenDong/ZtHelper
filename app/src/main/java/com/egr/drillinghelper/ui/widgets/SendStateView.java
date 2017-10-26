package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.egr.drillinghelper.R;

/**
 * author lzd
 * date 2017/10/26 16:49
 * 类描述：发送消息的状态
 */

public class SendStateView extends android.support.v7.widget.AppCompatImageView {

    private int frameCount,duration;
    public SendStateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAnimation(attrs);
    }

    public SendStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAnimation(attrs);
    }

    public SendStateView(Context context) {
        super(context);
    }

    private void setAnimation(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressView);
        frameCount = a.getInt(R.styleable.ProgressView_frameCount, 12);
        duration = a.getInt(R.styleable.ProgressView_duration, 1000);
        a.recycle();

        setSendState(2);
    }

    private void setAnimation() {
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

    public void setSendState(int state){
        switch (state){
            case 0:
                setVisibility(View.GONE);
                break;
            case 1:
                setVisibility(View.VISIBLE);
                clearAnimation();
                setImageResource(R.drawable.ic_send_fail);
                break;
            case 2:
                setVisibility(View.VISIBLE);
                setImageResource(R.drawable.ic_ring);
                setAnimation();
                break;
        }
    }
}
