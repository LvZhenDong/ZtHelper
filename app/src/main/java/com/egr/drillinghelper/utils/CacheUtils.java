package com.egr.drillinghelper.utils;

import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.common.MyConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static android.R.attr.id;

/**
 * author lzd
 * date 2017/10/12 13:49
 * 类描述：
 */

public class CacheUtils {

    /**
     * 保存使用知识问答
     *
     * @param data
     */
    public static void saveKnows(List<Explain> data){
        if (CollectionUtil.isListEmpty(data))
            return;
        Gson gson = new Gson();
        String cache = gson.toJson(data);
        try {
            FileUtils.writeFile(MyConstants.PATH_KNOW, cache, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取知识问答list
     * @return
     * @throws Exception
     */
    public static List<Explain> getKnows() throws Exception {
        Gson gson = new Gson();
        String cache = FileUtils.readFile(MyConstants.PATH_KNOW, null);
        List<Explain> explains = gson.fromJson(cache, new TypeToken<List<Explain>>() {
        }.getType());

        return explains;
    }

    public static Explain getKnow(String id) throws Exception{
        List<Explain> explains=getKnows();
        if(!CollectionUtil.isListEmpty(explains)){
            for (Explain explain:explains) {
                if(explain.getId().equals(id)){
                    return explain;
                }
            }
        }

        return null;
    }


    /**
     * 保存使用说明缓存
     *
     * @param data
     */
    public static void saveExplains(List<Explain> data) {
        if (CollectionUtil.isListEmpty(data))
            return;
        Gson gson = new Gson();
        String cache = gson.toJson(data);
        try {
            FileUtils.writeFile(MyConstants.PATH_EXPLAIN, cache, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取使用说明list
     * @return
     * @throws Exception
     */
    public static List<Explain> getExplains() throws Exception {
        Gson gson = new Gson();
        String cache = FileUtils.readFile(MyConstants.PATH_EXPLAIN, null);
        List<Explain> explains = gson.fromJson(cache, new TypeToken<List<Explain>>() {
        }.getType());

        return explains;
    }

    /**
     * 读取指定说明书目录
     * @param id
     * @return
     * @throws Exception
     */
    public static Explain getExplain(String id) throws Exception{
        List<Explain> explains=getExplains();
        if(!CollectionUtil.isListEmpty(explains)){
            for (Explain explain:explains) {
                if(explain.getId().equals(id)){
                    return explain;
                }
            }
        }

        return null;
    }
}