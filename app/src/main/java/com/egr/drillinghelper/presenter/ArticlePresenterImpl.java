package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.ArticleContract;
import com.egr.drillinghelper.model.ArticleModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ArticlePresenterImpl extends BasePresenter<ArticleContract.View,
        ArticleModelImpl> implements ArticleContract.Presenter {
    @Override
    protected IModel createModel() {
        return new ArticleModelImpl(this);
    }

    @Override
    public void getArticle(String catalogId,String id) {
        mModel.getArticle(catalogId,id);
    }
}
