package com.egr.drillinghelper.presenter;

import com.egr.drillinghelper.contract.KnowArticleContract;
import com.egr.drillinghelper.model.KnowArticleModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class KnowArticlePresenterImpl extends BasePresenter<KnowArticleContract.View,
        KnowArticleModelImpl> implements KnowArticleContract.Presenter{
    @Override
    protected IModel createModel() {
        return new KnowArticleModelImpl(this);
    }

    @Override
    public void getContent(String id) {
        mModel.getContent(id);
    }
}
