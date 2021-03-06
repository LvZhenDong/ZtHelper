package com.egr.drillinghelper.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.egr.drillinghelper.BuildConfig;
import com.egr.drillinghelper.common.MySharePreferencesManager;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.utils.EgrImageLoader;
import com.facebook.stetho.Stetho;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.pgyersdk.crash.PgyCrashManager;
import com.umeng.commonsdk.UMConfigure;

import cn.jpush.android.api.JPushInterface;
import cn.magicbeans.android.ipmanager.utils.MBIPUtils;

public class MyApplication extends MultiDexApplication {
    private static MyApplication sApplication;

    public static MyApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        registerActivityLifecycle();
        initPgy();
        initImagePicker();
        initOther();
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
    }


    private void initPgy() {
        PgyCrashManager.register(this);
    }

    private void initOther(){
        MySharePreferencesManager.getInstance().init(this);//secure sp init

        if(BuildConfig.DEBUG){
            String ip = MBIPUtils.getInstance(this).getIPPort();
            if (!TextUtils.isEmpty(ip)) {
                APIServiceFactory.setBaseUrl(ip);
            }
            Stetho.initializeWithDefaults(this);
        }
        JPushInterface.init(this);

    }

    private void initImagePicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new EgrImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private void registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                EgrAppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                EgrAppManager.getInstance().removeActivity(activity);
            }
        });
    }
}
