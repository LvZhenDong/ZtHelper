package com.egr.drillinghelper.ui.widgets;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.egr.drillinghelper.R;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * author lzd
 * date 2017/10/9 17:26
 * 类描述：
 */

public class ReadReplyDialog extends BaseBottomDialog {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.wv)
    WebView webView;

    String mContent;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_read_reply;
    }

    @Override
    public void bindView(View v) {
        ButterKnife.bind(this, v);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        webView.loadDataWithBaseURL("", mContent, "text/html", "utf-8", "");
    }

    public void showReply(FragmentManager manager,String content){
        show(manager);
        mContent=content;
    }
}
