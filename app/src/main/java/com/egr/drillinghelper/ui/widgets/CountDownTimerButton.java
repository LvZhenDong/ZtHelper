package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import com.egr.drillinghelper.R;

/**
 * 带倒计时的Button
 *
 * @author LvZhenDong
 *         created at 2017/4/19 10:35
 */
public class CountDownTimerButton extends android.support.v7.widget.AppCompatButton {
    private Context mContext;
    private CountDownTimer mCountDownTimer;
    private String mText;
    private long mSeconds;

    public CountDownTimerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable
                .CountDownTimerButton);
        mSeconds = typedArray.getInteger(R.styleable.CountDownTimerButton_time, 60);
//        mText=attrs.getAttributeValue("http://schemas.android.com/apk/res/android","text");
        mText=getResources().getString(R.string.get_ver_code);
        setBackground(getResources().getDrawable(R.drawable.bg_get_ver_code_btn_radius_selector));

        setEnabled(true);
        setMaxLines(1);
        setAllCaps(false);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(enabled)
            setText(mText);
    }

    /**
     * 开始倒计时
     */
    public void startCountDownTimer() {
        setEnabled(false);
        mCountDownTimer = new CountDownTimer(mSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                setEnabled(true);
            }
        };
        mCountDownTimer.start();
    }

    /**
     * 关闭倒计时Timer,要先调该方法，再Activity.onDestroy
     */
    public void stopCountDownTimer() {
        if (mCountDownTimer != null) mCountDownTimer.cancel();
    }
}
