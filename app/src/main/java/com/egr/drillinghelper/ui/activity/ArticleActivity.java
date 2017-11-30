package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.contract.ArticleContract;
import com.egr.drillinghelper.presenter.ArticlePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.StringUtils;
import com.egr.drillinghelper.utils.ToastUtils;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/9/29 20:55
 * 类描述：文章详情
 */

public class ArticleActivity extends BaseMVPActivity<ArticleContract.View,
        ArticlePresenterImpl> implements ArticleContract.View {
    @BindView(R.id.wv)
    WebView webView;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_article;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.explain);
        setupActionBar(R.string.explain, true);
        setActionbarBackground(R.color.white);

        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); //不显示webview缩放按钮
//        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        String id = getIntent().getStringExtra(KEY_INTENT);
        String catalogId = getIntent().getStringExtra("catalogId");

        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getArticle(catalogId, id);
    }

    @Override
    public ArticlePresenterImpl createPresenter() {
        return new ArticlePresenterImpl();
    }

    @Override
    public void getArticleSuccess(Article article) {
        mDialog.dismiss();

        if (article != null) {
            String content = article.getContent();
            if (article.isCache()) {
                content = StringUtils.updateHtmlTag(content, "img", "src");
            }
            content = StringUtils.getNewContent(content);
            webView.loadDataWithBaseURL("", content, "text/html", "utf-8", "");
        }

    }

    @Override
    public void getArticleFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }
}
