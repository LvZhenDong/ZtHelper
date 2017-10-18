package com.egr.drillinghelper.hybrid;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * @author Ymmmsick
 * @date 2017-05-16 18:19:08
 */
public class LWebViewClient extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }


    /**
     * API 21 以上用shouldInterceptRequest(WebView view, WebResourceRequest request)
     *
     * @param view
     * @param request
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//        String url = request.getUrl().toString();
//        Logger.i("request url --->" + url);
//        if (url.contains("browser-c6a8b99c7cc1d3faccfa.min.js")) {
//            resourceReplace("browser-c6a8b99c7cc1d3faccfa.min.js", "application/x-javascript");
//        }
//        if (url.contains("common-82a1b3739e7b0dcf36a6.min.js")) {
//            resourceReplace("common-82a1b3739e7b0dcf36a6.min.js", "application/x-javascript");
//        }
//        if (url.contains("browser-8fd1bfa7227d99749003f7468d276552.min.css")) {
//            resourceReplace("browser-8fd1bfa7227d99749003f7468d276552.min.css", "text/css");
//        }
//        if (url.contains("style1.3.27.7.css")) {
//            resourceReplace("style1.3.27.7.css", "text/css");
//        }
        return super.shouldInterceptRequest(view, request);
    }

    /**
     * API 21 以下用shouldInterceptRequest(WebView view, String url)
     *
     * @param view
     * @param url
     * @return
     */
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//        Logger.i("request url --->" + url);
//        if (url.contains("browser")) {
//            resourceReplace("browser-c6a8b99c7cc1d3faccfa.min.js", "application/x-javascript");
//        }
//        if (url.contains("common")) {
//            resourceReplace("common-82a1b3739e7b0dcf36a6.min.js", "application/x-javascript");
//        }
        return super.shouldInterceptRequest(view, url);
    }
}