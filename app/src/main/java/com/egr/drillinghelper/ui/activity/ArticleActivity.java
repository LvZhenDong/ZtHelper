package com.egr.drillinghelper.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.app.EgrAppManager;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.contract.ArticleContract;
import com.egr.drillinghelper.hybrid.JSInterfaceSO;
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

    @SuppressLint("JavascriptInterface")
    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.explain);
        setupActionBar(R.string.explain, true);
        setActionbarBackground(R.color.white);
        setActionBarRightText(R.string.back_home, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EgrAppManager.getInstance().finishOtherActivity(HomeActivity.class);
            }
        });

        webView.addJavascriptInterface(new JSInterfaceSO(this), "JSInterfaceSO");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                addImageClickListener();
            }
        });
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

    private void addImageClickListener() {
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.JSInterfaceSO.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }
}
