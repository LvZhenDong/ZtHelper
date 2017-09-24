package com.kklv.fragme.app;

import android.support.multidex.MultiDexApplication;

import com.kklv.fragme.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
    }

    private void initLogger(){
//        FormatStrategy formatStrategy= PrettyFormatStrategy.newBuilder()
//                .tag("kklv")
//                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
