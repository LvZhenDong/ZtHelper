package com.egr.drillinghelper.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 *属性动画的包装类
 * 
 * @author LvZhenDong
 * created at 2017/6/16 9:35
 */
public class AnimViewWrapper {

    private AnimViewWrapper() {
    }

    /**
     * 需要改变marginLeft的View
     */
    public static class MarginLeft{
        private View mTarget;
        private Context mContext;

        public MarginLeft(View target,Context context) {
            mTarget = target;
            mContext=context;
        }

        public int getMarginLeft() {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTarget
                    .getLayoutParams();
            return (int) DensityUtils.px2dp(mContext, params.leftMargin);
        }

        public void setMarginLeft(int marginLeft) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTarget
                    .getLayoutParams();
            params.setMargins(DensityUtils.dp2px(mContext, marginLeft), params
                    .topMargin, params.rightMargin, params.bottomMargin);
            mTarget.setLayoutParams(params);
        }
    }

    /**
     * 需要改变width的View
     */
    public static class Width {
        private View mTarget;
        private Context mContext;

        public Width(View target, Context context) {
            mTarget = target;
            mContext = context;
        }

        public int getWidth() {
            return (int) DensityUtils.px2dp(mContext, mTarget.getLayoutParams
                    ().width);
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = DensityUtils.dp2px(mContext,
                    width);
            mTarget.requestLayout();
        }
    }
}
