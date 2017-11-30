package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.contract.KnowArticleContract;
import com.egr.drillinghelper.hybrid.JSInterfaceSO;
import com.egr.drillinghelper.presenter.KnowArticlePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.StringUtils;

import butterknife.BindView;

/**
 * author lzd
 * date 2017/10/10 10:02
 * 类描述：知识问答详情
 */

public class KnowArticleActivity extends BaseMVPActivity<KnowArticleContract.View, KnowArticlePresenterImpl>
        implements KnowArticleContract.View {
    @BindView(R.id.wv)
    WebView webView;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_article;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setUmengAnalyze(R.string.ask_knowledge_detail);
        setupActionBar(R.string.ask_knowledge_detail, true);
        setActionbarBackground(R.color.white);

        webView.addJavascriptInterface(new JSInterfaceSO(this), "JSInterfaceSO");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                addImageClickListener();
            }
        });
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false); //不显示webview缩放按钮

        String content = getIntent().getStringExtra(KEY_INTENT);
        boolean isCache = getIntent().getBooleanExtra(KEY_INTENT_BOOLEAN, false);

        if (isCache) {
            content = StringUtils.updateHtmlTag(content, "img", "src");
        }
        content = StringUtils.getNewContent(content);
        webView.loadDataWithBaseURL(null, content, null, "utf-8", null);

        //单纯为了后台统计用户查看问题次数
        presenter.getContent(getIntent().getStringExtra("id"));
    }

    @Override
    public KnowArticlePresenterImpl createPresenter() {
        return new KnowArticlePresenterImpl();
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
