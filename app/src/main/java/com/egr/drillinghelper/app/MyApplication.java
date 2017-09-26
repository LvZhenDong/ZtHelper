package com.egr.drillinghelper.app;

import android.support.multidex.MultiDexApplication;

import com.egr.drillinghelper.BuildConfig;
import com.egr.drillinghelper.common.MySharePreferencesManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.pgyersdk.crash.PgyCrashManager;

public class MyApplication extends MultiDexApplication {
    private static MyApplication sApplication;

    public static MyApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        initPgy();
        initLogger();
        initOther();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("kklv")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initPgy() {
        PgyCrashManager.register(this);
    }

    private void initOther(){
        MySharePreferencesManager.getInstance().init(this);//secure sp init
    }
}
