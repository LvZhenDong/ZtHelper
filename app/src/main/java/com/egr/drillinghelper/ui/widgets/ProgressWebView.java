package com.egr.drillinghelper.ui.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.egr.drillinghelper.R;


/**
 * @author gyw
 * @version 1.0
 * @time: 2015-12-25 下午1:08:25
 * @fun:
 * @fix: 修改加载失败后的中午显示
 */
public class ProgressWebView extends LinearLayout {

    WebView mWebView;
    ProgressBar mProgressBar;

    private Context mContext;

    private String url;

    public ProgressWebView(Context context) {
        this(context, null);
    }


    public ProgressWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView(context);
        initWebview();
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.view_web_progress, this);
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void loadUrl(String url) {
        this.url = url;
        mWebView.loadUrl(url);
    }

    public WebSettings getSetting() {
        return mWebView.getSettings();
    }

    public WebView getmWebView() {
        return mWebView;
    }

    public ProgressBar getmProgressview() {
        return mProgressBar;
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public void goBack() {
        mWebView.goBack();
    }

    public void reload() {
        mWebView.reload();
    }

    public void destroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
    }

    private void initWebview() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setDomStorageEnabled(true);
        // 设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            // 页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        // 设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient() {
            // 设置网页加载的进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
    }
}
