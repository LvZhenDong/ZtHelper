package com.egr.drillinghelper.model;

import android.text.TextUtils;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.SearchContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
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

public class SearchModelImpl extends BaseModel<SearchPresenterImpl> implements SearchContract.Model {
    NetApi api;

    public SearchModelImpl(SearchPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void searchKnow(final String keyword, int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.searchKnow(keyword, current + "")
                    .compose(TransformersFactory.<BasePage<KnowCatalog>>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<BasePage<KnowCatalog>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showKnowCache(keyword);
                            else
                                presenter.getView().searchFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<KnowCatalog> data) {
                            presenter.searchKnowSuccess(data);

                        }
                    });
        } else {
            showKnowCache(keyword);
        }
    }

    @Override
    public void searchParts(final String keyword, int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.searchProduct(keyword, current + "")
                    .compose(TransformersFactory.<BasePage<Store>>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Store>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showPartsCache(keyword);
                            else
                                presenter.getView().searchFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Store> data) {
                            presenter.searchPartsSuccess(data);

                        }
                    });
        } else {
            showPartsCache(keyword);
        }
    }

    @Override
    public void searchExplain(final String keyword, int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.searchExplain(keyword, current + "")
                    .compose(TransformersFactory.<BasePage<Explain>>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Explain>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showExplainCache(keyword);
                            else
                                presenter.getView().searchFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Explain> data) {
                            presenter.searchExplainCatalogSuccess(data);
                        }
                    });
        } else {
            showExplainCache(keyword);
        }
    }

    private void showExplainCache(String keyword) {
        try {
            BasePage<Explain> basePage = new BasePage<>();
            basePage.setRecords(searchExplainsInCache(keyword));
            presenter.searchExplainCatalogSuccess(basePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Explain> searchExplainsInCache(String keyword) throws Exception {
        List<Explain> searchResult = new ArrayList<>();
        List<Explain> cacheList = CacheUtils.getExplains();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) return cacheList;
        List<String> splitKeywords=StringUtils.splitKeyword(keyword.toLowerCase());
        for (Explain item : cacheList) {
            String title=item.getTitle();
            if(TextUtils.isEmpty(title))break;    //如果标题为null
            if(StringUtils.stringContainsItemFromList(title.toLowerCase(),splitKeywords)){
                searchResult.add(item);
            }
        }

        return searchResult;
    }

    private void showPartsCache(String keyword) {
        try {
            BasePage<Store> basePage = new BasePage<>();
            basePage.setRecords(searchPartsInCache(keyword));
            presenter.searchPartsSuccess(basePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Store> searchPartsInCache(String keyword) throws Exception {
        List<Store> searchResult = new ArrayList<>();
        List<Store> cacheList = CacheUtils.getParts();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) return cacheList;
        List<String> splitKeywords=StringUtils.splitKeyword(keyword.toLowerCase());
        for (Store item : cacheList) {
            String title=item.getName();
            if(TextUtils.isEmpty(title))break;    //如果标题为null
            if(StringUtils.stringContainsItemFromList(title.toLowerCase(),splitKeywords)){
                searchResult.add(item);
            }
        }

        return searchResult;
    }

    private void showKnowCache(String keyword) {
        try {
            BasePage<KnowCatalog> basePage = new BasePage<>();
            basePage.setRecords(searchKnowInCache(keyword));
            presenter.searchKnowSuccess(basePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<KnowCatalog> searchKnowInCache(String keyword) throws Exception {
        List<KnowCatalog> searchResult = new ArrayList<>();
        List<KnowCatalog> cacheList = new ArrayList<>();
        List<Explain> explainList = CacheUtils.getKnows();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(explainList)) return cacheList;
        List<String> splitKeywords=StringUtils.splitKeyword(keyword.toLowerCase());
        for (Explain item : explainList) {
            cacheList.addAll(item.getKnows());
        }

        for (KnowCatalog catalog : cacheList) {
            String title=catalog.getTitle();
            if(TextUtils.isEmpty(title))break;    //如果标题为null
            if(StringUtils.stringContainsItemFromList(title.toLowerCase(),splitKeywords)){
                searchResult.add(catalog);
            }
        }

        return searchResult;
    }
}
