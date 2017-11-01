package com.egr.drillinghelper.model;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.contract.ExplainContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.ExplainPresenterImpl;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.EgrTarget;
import com.egr.drillinghelper.utils.FileUtils;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.NetworkUtils;
import com.egr.drillinghelper.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.api.error.ERROR.TIMEOUT_ERROR;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class ExplainModelImpl extends BaseModel<ExplainPresenterImpl> implements ExplainContract.Model {

    private NetApi api;

    public ExplainModelImpl(ExplainPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getExplainList(final String keyword, int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.explainList(keyword, current + "")
                    .compose(TransformersFactory.<BasePage<Explain>>commonTransformer((BaseMVPFragment) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Explain>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache(keyword);
                            else
                                presenter.getExplainFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Explain> data) {
                            presenter.getExplainListSuccess(data);
                        }
                    });
        } else {
            showCache(keyword);
        }
    }

    private void showCache(String keyword) {
        try {
            presenter.getView().showExplainCache(searchExplainsInCache(keyword));
        } catch (Exception e) {
            presenter.getExplainFail(getContext().getString(R.string.net_error));
        }
    }

    private List<Explain> searchExplainsInCache(String keyword) throws Exception {
        List<Explain> searchResult = new ArrayList<>();
        List<Explain> cacheList = CacheUtils.getExplains();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) return cacheList;
        for (Explain item : cacheList) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || item.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(item);
            }
        }

        return searchResult;
    }

    @Override
    public void getExplainCache() {
        api.getExplainCache()
                .compose(TransformersFactory.<List<Explain>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<List<Explain>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull List<Explain> data) {
                        CacheUtils.saveExplains(data);
                        saveImg();
                    }
                });
    }

    private void saveImg() {
        try {
            List<Explain> explains = CacheUtils.getExplains();
            for (Explain item : explains) {
                if (!TextUtils.isEmpty(item.getPhoto())) {//下载list里的图片
                    GlideUtils.perLoadImg(getContext(), item.getPhoto());
                }
                List<ExplainCatalog> explainCatalogs = CacheUtils.getExpandedExplainCatalogList(item.getId());
                for (ExplainCatalog catalog : explainCatalogs) {
                    if (!TextUtils.isEmpty(catalog.getArticleId()) && !catalog.getArticleId().equals("0")) {
                        Article article = catalog.getArticle();
                        String content = article.getContent();
                        List<String> imgs = StringUtils.match(content, "img", "src");
                        if (!CollectionUtil.isListEmpty(imgs)) {  //下载图片
                            for (String path : imgs) {
                                String[] strs = path.split("/");
                                String name = strs[strs.length - 1];
                                if (!TextUtils.isEmpty(name) && !FileUtils.fileExists(MyConstants.PATH + name))
                                    Glide.with(getContext()).load(path).into(new EgrTarget(name));
                            }

                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
