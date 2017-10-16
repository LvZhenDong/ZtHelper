package com.egr.drillinghelper.hybrid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
    private boolean isActionbarEnable;

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
        isActionbarEnable = intent.getBooleanExtra("isActionbarEnable", false);

        initActionBar();
        initWebView();
//        LLRxBus.subscribe(this, String.class, consumer);
//        LLCookieManager.syncCookie(this, url);
        commbrowserWebview.loadUrl(url);
    }

    private void initActionBar() {
        setupActionBar(R.string.browser_title_default);
        setActionBarTitle(title);
        setActionBarLeftIcon(R.drawable.ic_arrow_back, new View.OnClickListener() {
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
        commbrowserWebview.getmWebView().setWebViewClient(new LWebViewClient());
        commbrowserWebview.getmWebView().setWebChromeClient(new LWebChromeClient());
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(NetworkUtils.isNetworkConnected(this) ? WebSettings.LOAD_DEFAULT : WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
//        设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
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
        super.onDestroy();
        if (commbrowserWebview != null)
            commbrowserWebview.destroy();
    }

    public class LWebChromeClient extends WebChromeClient {

        @Override
        public void onReceivedTitle(WebView webView, String s) {

        }

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

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, getString(R.string.choose_img)), FILECHOOSER_RESULTCODE);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, getString(R.string.choose_img)), FILECHOOSER_RESULTCODE);
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
