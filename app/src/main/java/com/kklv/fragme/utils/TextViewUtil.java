package com.kklv.fragme.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * 类描述：TextView工具类
 */

public class TextViewUtil {

    public static void setText(TextView tv,String text){
        if(TextUtils.isEmpty(text)){
            tv.setVisibility(View.GONE);
        }else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    public static void setText(TextView tv,String head,String text){
        if(TextUtils.isEmpty(text)){
            tv.setVisibility(View.GONE);
        }else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(head+text);
        }
    }
}
