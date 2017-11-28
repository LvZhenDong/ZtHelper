package com.egr.drillinghelper.hybrid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.ui.base.BaseActivity;
import com.egr.drillinghelper.ui.widgets.ProgressWebView;
import com.egr.drillinghelper.utils.FileUtils;
import com.egr.drillinghelper.utils.NetworkUtils;

import java.io.File;

import butterknife.BindView;

/**
 * base browser activity for common url load
 */

public class CommBrowserActivity extends BaseActivity {

    @BindView(R.id.commbrowser_webview)
    public ProgressWebView commbrowserWebview;

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;
    private final int INPUT_FILE_REQUEST_CODE = 1;
    private final int FILECHOOSER_RESULTCODE = 2;
    private String url,title;
    private Intent intent;
    private WebSettings webSettings;

    public static void start(Context context,String url,String title){
        Intent intent=new Intent(context, CommBrowserActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    @Override
    public int returnLayoutID() {
        return R.layout.activity_basebrowser;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

        initActionBar();
        initWebView();
        commbrowserWebview.loadUrl(url);
    }

    private void initActionBar() {
        setupActionBar(title);
        setActionBarLeftIcon(R.drawable.ic_egr_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressBack();
            }
        });
        setActionbarBackground(R.color.white);
        setActionBarLeft2Icon(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("JavascriptInterface")
    protected void initWebView() {
        webSettings = commbrowserWebview.getSetting();
        commbrowserWebview.getmWebView().addJavascriptInterface(new JSInterfaceSO(this), "JSInterfaceSO");
        commbrowserWebview.getmWebView().setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (commbrowserWebview != null && commbrowserWebview.getmProgressview() != null) {
                    commbrowserWebview.getmProgressview().setVisibility(View.VISIBLE);
                }
            }
        });
        commbrowserWebview.getmWebView().setWebChromeClient(new LWebChromeClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);         //提高渲染优先级
        webSettings.setCacheMode(NetworkUtils.isNetworkConnected(this) ? WebSettings.LOAD_DEFAULT : WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
//        设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 尽可能大的视野展示页面
        //低版本关闭硬件加速，防止花屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            commbrowserWebview.getmWebView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            commbrowserWebview.getmWebView().setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }


    @Override
    protected void onDestroy() {
        WebView mWebView=commbrowserWebview.getmWebView();
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    public class LWebChromeClient extends WebChromeClient {

        @Override
        // android 5.0 这里需要使用android5.0 sdk
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            }
            mFilePathCallback = filePathCallback;
            /**
             标准意图，被发送到相机应用程序捕获一个图像，并返回它。通过一个额外的extra_output控制这个图像将被写入。如果extra_output是不存在的，
             那么一个小尺寸的图像作为位图对象返回。如果extra_output是存在的，那么全尺寸的图像将被写入extra_output URI值。
             */
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    //设置MediaStore.EXTRA_OUTPUT路径,相机拍照写入的全路径
                    photoFile = FileUtils.createImageFile();
                    mCameraPhotoPath = photoFile.getAbsolutePath();
                    takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                } catch (Exception ex) {
                    // Error occurred while creating the File
                    Log.e("WebViewSetting", "Unable to create Image File", ex);
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                } else {
                    takePictureIntent = null;
                }
            }
            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType("image/*");
            Intent[] intentArray;
            if (takePictureIntent != null) {
                intentArray = new Intent[]{takePictureIntent};
                System.out.println(takePictureIntent);
            } else {
                intentArray = new Intent[0];
            }
            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            chooserIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.choose_img));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
            return true;
        }

        //For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, getString(R.string.choose_img)), FILECHOOSER_RESULTCODE);

        }

        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            if (commbrowserWebview != null && commbrowserWebview.getmProgressview() != null) {
                commbrowserWebview.getmProgressview().setProgress(newProgress);
                if (newProgress == 100)
                    commbrowserWebview.getmProgressview().setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        pressBack();
    }

    private void pressBack() {
        if (commbrowserWebview.canGoBack()) {
                commbrowserWebview.goBack();
        } else {
            finish();
        }
    }
}
