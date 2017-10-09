package com.egr.drillinghelper.utils;

import java.util.List;

/**
 * author lzd
 * date 2017/10/9 16:20
 * 类描述：
 */

public class CollectionUtil {

    public static boolean isListEmpty(List list){
        if(list == null || list.size()==0)
            return true;
        else
            return false;
    }
}
