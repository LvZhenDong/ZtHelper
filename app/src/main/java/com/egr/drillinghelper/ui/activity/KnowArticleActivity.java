package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseActivity;

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

        String content = getIntent().getStringExtra(KEY_INTENT);

        webView.loadDataWithBaseURL(null,content,null,"utf-8",null);
    }
}
