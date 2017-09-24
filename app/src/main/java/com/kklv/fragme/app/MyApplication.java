package com.kklv.fragme.app;

import android.support.multidex.MultiDexApplication;

import com.kklv.fragme.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.pgyersdk.crash.PgyCrashManager;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initPgy();
        initLogger();
    }

    private void initLogger(){
        FormatStrategy formatStrategy= PrettyFormatStrategy.newBuilder()
                .tag("kklv")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initPgy(){
        PgyCrashManager.register(this);
    }
}
