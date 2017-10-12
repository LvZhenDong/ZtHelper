package com.egr.drillinghelper.common;

import android.os.Environment;

/**
 * Created by lvzhendong on 2017/9/24.
 */

public interface MyConstants {

    String TAG_COOKIE = "Cookie";

    String PATH = Environment
            .getExternalStorageDirectory().getPath() + "/egr/";
    String PATH_EXPLAIN = PATH + "explain.txt";
    String PATH_KNOW = PATH + "know.txt";

    interface API {
        String Version = "version/check?code=";
    }
}
