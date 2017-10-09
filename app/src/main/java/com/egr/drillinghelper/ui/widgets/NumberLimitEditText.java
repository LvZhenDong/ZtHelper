package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.egr.drillinghelper.R;

/**
 * author lzd
 * date 2017/9/30 15:45
 * 类描述：输入字符数提示EditText
 */

public class NumberLimitEditText extends RelativeLayout {

    private static final int DEFAULT_LIMIT_NUM = 200;   //默认输入限制
    private static final int DEFAULT_HINT_COLOR = 0X999999; //默认hint颜色
    private static final int DEFAULT_ET_TEXT_SIZE = 14;     //默认输入框字体大小
    private static final int DEFAULT_TV_TEXT_SIZE = 12;     //默认number显示字体大小

    private Context mContext;
    private EditText mEt;
    private TextView mTv;

    private int limitNum = DEFAULT_LIMIT_NUM;

    public NumberLimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext
                .obtainStyledAttributes(attrs, R.styleable.NumberLimitEditText);
        limitNum = typedArray.getInt(R.styleable.NumberLimitEditText_limitNum, DEFAULT_LIMIT_NUM);
        createEt(typedArray);
        createTv(typedArray);
    }

    private void createEt(TypedArray typedArray) {
        mEt = new EditText(mContext);
        //获取xml属性
        String hint = typedArray.getString(R.styleable.NumberLimitEditText_hint);
        int color = typedArray.getColor(R.styleable.NumberLimitEditText_textColorHint, DEFAULT_HINT_COLOR);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.NumberLimitEditText_textSize, DEFAULT_ET_TEXT_SIZE);
        //设置xml属性
        mEt.setHint(hint);
        mEt.setHintTextColor(color);
        mEt.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        //设置layoutParams
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mEt.setLayoutParams(params);
        mEt.setBackground(null);
        mEt.setGravity(Gravity.TOP | Gravity.LEFT);
        //设置监听
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = s.length();
                if (count <= limitNum) {
                    showLeftNum(count);
                } else {
                    mEt.setText(mEt.getText().toString().substring(0, limitNum)); //截断之后输入的文字
                    mEt.setSelection(limitNum); //移动光标到末尾
                }
            }
        });

        addView(mEt);
    }

    private void createTv(TypedArray typedArray) {
        mTv = new TextView(mContext);
        int color = typedArray.getColor(R.styleable.NumberLimitEditText_textColorLimitNum, DEFAULT_HINT_COLOR);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.NumberLimitEditText_textSizeLimitNum, DEFAULT_TV_TEXT_SIZE);

        mTv.setTextColor(color);
        mTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(ALIGN_PARENT_RIGHT);
        mTv.setText(getResources().getString(R.string.number_limit_et_hint) + limitNum
                + getResources().getString(R.string.number_limit_et_hint2));
        mTv.setLayoutParams(params);
        addView(mTv);
    }

    private void showLeftNum(int num) {
        mTv.setText(getResources().getString(R.string.number_limit_et_hint) + (limitNum - num)
                + getResources().getString(R.string.number_limit_et_hint2));
    }
}
