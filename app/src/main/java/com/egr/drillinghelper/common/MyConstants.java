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
    String PATH_STORE=PATH+"store.txt";


    int SEARCH_TYPE_EXPLAIN = 0;
    int SEARCH_TYPE_KNOWLEDGE = 1;
    int SEARCH_TYPE_PARTS = 2;
}
