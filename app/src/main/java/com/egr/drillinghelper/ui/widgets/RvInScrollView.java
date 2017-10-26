package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * author lzd
 * date 2017/10/26 15:26
 * 类描述：
 */

public class RvInScrollView extends RecyclerView {
    public RvInScrollView(Context context) {
        super(context);
    }

    public RvInScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RvInScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int spec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, spec);
    }
}
