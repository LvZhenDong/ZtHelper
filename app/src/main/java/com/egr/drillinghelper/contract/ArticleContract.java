package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface ArticleContract {
    interface Model extends IModel {
        void getArticle(String id);
    }

    interface View extends IView {
        void getArticleSuccess(Article article);

        void getArticleFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getArticle(String id);
    }
}
