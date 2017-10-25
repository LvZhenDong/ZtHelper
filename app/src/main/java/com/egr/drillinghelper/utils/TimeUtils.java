package com.egr.drillinghelper.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author lzd
 * date 2017/10/25 18:00
 * 类描述：
 */

public class TimeUtils {

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());

        return formatter.format(curDate);
    }
}
