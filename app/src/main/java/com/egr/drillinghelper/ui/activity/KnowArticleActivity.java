package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.utils.StringUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/10/10 10:02
 * 类描述：知识问答详情
 */

public class KnowArticleActivity extends BaseActivity {
    @BindView(R.id.wv)
    WebView webView;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_article;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.ask_knowledge_detail, true);
        setActionbarBackground(R.color.white);

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false); //不显示webview缩放按钮

        String content = getIntent().getStringExtra(KEY_INTENT);
        boolean isCache=getIntent().getBooleanExtra(KEY_INTENT_BOOLEAN,false);

        if (isCache) {
            content = StringUtils.updateHtmlTag(content, "img", "src");
            Logger.i("change:" + content);
        }
        webView.loadDataWithBaseURL(null,content,null,"utf-8",null);
    }
}
