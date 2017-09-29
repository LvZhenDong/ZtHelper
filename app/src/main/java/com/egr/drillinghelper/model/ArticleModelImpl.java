package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.contract.ArticleContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ArticlePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ArticleModelImpl extends BaseModel<ArticlePresenterImpl> implements ArticleContract.Model {
    private NetApi api;

    public ArticleModelImpl(ArticlePresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService(NetApi.class);
    }

    @Override
    public void getArticle(String id) {
        api.getArticle(id)
                .compose(TransformersFactory.<Article>commonTransformer((BaseMVPActivity) presenter.getView()))
                .subscribe(new EObserver<Article>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getArticleFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Article article) {
                        presenter.getView().getArticleSuccess(article);
                    }
                });
    }
}
