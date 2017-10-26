package com.egr.drillinghelper.ui.widgets;


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * author lzd
 * date 2017/10/26 10:59
 * 类描述：监听回车事件
 */

public class LvEditText extends android.support.v7.widget.AppCompatEditText {
    private OnEnterListener mOnEnterListener;

    public LvEditText(Context context) {
        super(context);
        init();
    }

    public LvEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LvEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT ||actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                        && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    String text = getText().toString().trim();
                    if (mOnEnterListener != null)
                        mOnEnterListener.onEnterClick(text);

                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    public String getTrimText() {
        return getText().toString().trim();
    }

    /**
     * 设置回车按钮监听
     *
     * @param listener
     */
    public void setOnEnterListener(OnEnterListener listener) {
        mOnEnterListener = listener;
    }

    public interface OnEnterListener {
        void onEnterClick(String text);
    }
}
