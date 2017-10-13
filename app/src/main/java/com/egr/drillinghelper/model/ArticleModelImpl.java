package com.egr.drillinghelper.model;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.contract.ArticleContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ArticlePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.api.error.ERROR.TIMEOUT_ERROR;
import static com.pgyersdk.views.b.p;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ArticleModelImpl extends BaseModel<ArticlePresenterImpl> implements ArticleContract.Model {
    private NetApi api;
    private List<ExplainCatalog> list;

    public ArticleModelImpl(ArticlePresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getArticle(final String catalogId, final String id) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.getArticle(id)
                    .compose(TransformersFactory.<Article>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<Article>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache(catalogId, id);
                            else if(eMsg.equals("未知错误"))
                                presenter.getView().getArticleSuccess(null);
                            else
                                presenter.getView().getArticleFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull Article article) {
                            presenter.getView().getArticleSuccess(article);
                        }
                    });
        } else {
            showCache(catalogId, id);
        }
    }

    private void showCache(String catalogId, String id) {
        try {
            Explain explain = CacheUtils.getExplain(catalogId);
            if (explain != null) {
                list = new ArrayList<>();
                getList(explain.getCatalogs());
                if (!CollectionUtil.isListEmpty(list)) {
                    for (ExplainCatalog item : list) {
                        if (item.getArticleId().equals(id)) {
                            presenter.getView().getArticleSuccess(item.getArticle());
                            return;
                        }
                    }
                }
            }
            presenter.getView().getArticleFail(getContext().getString(R.string.no_data));

        } catch (Exception e) {
            presenter.getView().getArticleFail(getContext().getString(R.string.net_error));
        }
    }

    private void getList(List<ExplainCatalog> catalogList) {

        if (CollectionUtil.isListEmpty(catalogList))
            return;

        for (ExplainCatalog item : catalogList) {
            list.add(item);
            if (item.getChilds() != null && item.getChilds().size() != 0)
                getList(item.getChilds());
        }

    }
}
