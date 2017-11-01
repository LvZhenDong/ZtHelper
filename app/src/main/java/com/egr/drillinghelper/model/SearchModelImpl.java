package com.egr.drillinghelper.model;

import android.text.TextUtils;

import com.egr.drillinghelper.R;
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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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
            searchExplainsInCache(keyword);
        } catch (Exception e) {
            presenter.getView().searchFail(getContext().getString(R.string.net_error));
        }
    }


    private void searchExplainsInCache(final String keyword) throws Exception {
        final List<Explain> searchResult = new ArrayList<>();
        final List<Explain> cacheList = CacheUtils.getExplains();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) {
            BasePage<Explain> basePage = new BasePage<>();
            basePage.setCacheRecords(searchResult);
            presenter.searchExplainCatalogSuccess(basePage);

            return;
        }

        //这里因为ansj_seg这个分词库第一次加载到内存是时候很耗时（大概有5、6s），所以在子线程中进行分词
        Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                        e.onNext(StringUtils.splitKeyword(keyword.toLowerCase()));
                    }
                })
                .compose(TransformersFactory.<List<String>>defaultSchedulers())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        for (Explain item : cacheList) {
                            String title = item.getTitle();
                            if (TextUtils.isEmpty(title)) continue;    //如果标题为null
                            if (StringUtils.stringContainsItemFromList(title.toLowerCase(), strings)) {
                                searchResult.add(item);
                            }
                        }

                        BasePage<Explain> basePage = new BasePage<>();
                        basePage.setCacheRecords(searchResult);
                        presenter.searchExplainCatalogSuccess(basePage);
                    }
                });
    }

    private void showPartsCache(String keyword) {
        try {
            searchPartsInCache(keyword);
        } catch (Exception e) {
            presenter.getView().searchFail(getContext().getString(R.string.net_error));
        }
    }

    private void searchPartsInCache(final String keyword) throws Exception {
        final List<Store> searchResult = new ArrayList<>();
        final List<Store> cacheList = CacheUtils.getParts();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) {
            BasePage<Store> basePage = new BasePage<>();
            basePage.setCacheRecords(searchResult);
            presenter.searchPartsSuccess(basePage);

            return;
        }

        //这里因为ansj_seg这个分词库第一次加载到内存是时候很耗时（大概有5、6s），所以在子线程中进行分词
        Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                        e.onNext(StringUtils.splitKeyword(keyword.toLowerCase()));
                    }
                })
                .compose(TransformersFactory.<List<String>>defaultSchedulers())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        for (Store item : cacheList) {
                            String title = item.getName();
                            if (TextUtils.isEmpty(title)) continue;    //如果标题为null
                            if (StringUtils.stringContainsItemFromList(title.toLowerCase(), strings)) {
                                searchResult.add(item);
                            }
                        }

                        BasePage<Store> basePage = new BasePage<>();
                        basePage.setCacheRecords(searchResult);
                        presenter.searchPartsSuccess(basePage);
                    }
                });
    }

    private void showKnowCache(String keyword) {
        try {
            searchKnowInCache(keyword);
        } catch (Exception e) {
            presenter.getView().searchFail(getContext().getString(R.string.net_error));
        }
    }

    private void searchKnowInCache(final String keyword) throws Exception {
        final List<KnowCatalog> searchResult = new ArrayList<>();
        final List<KnowCatalog> cacheList = new ArrayList<>();
        final List<Explain> explainList = CacheUtils.getKnows();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(explainList)) {
            BasePage<KnowCatalog> basePage = new BasePage<>();
            basePage.setCacheRecords(searchResult);
            presenter.searchKnowSuccess(basePage);
        }

        //这里因为ansj_seg这个分词库第一次加载到内存是时候很耗时（大概有5、6s），所以在子线程中进行分词
        Observable
                .create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                        e.onNext(StringUtils.splitKeyword(keyword.toLowerCase()));
                    }
                })
                .compose(TransformersFactory.<List<String>>defaultSchedulers())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {

                        for (Explain item : explainList) {
                            cacheList.addAll(item.getKnows());
                        }

                        for (KnowCatalog catalog : cacheList) {
                            String title = catalog.getTitle();
                            if (TextUtils.isEmpty(title)) continue;    //如果标题为null
                            if (StringUtils.stringContainsItemFromList(title.toLowerCase(), strings)) {
                                searchResult.add(catalog);
                            }
                        }

                        BasePage<KnowCatalog> basePage = new BasePage<>();
                        basePage.setCacheRecords(searchResult);
                        presenter.searchKnowSuccess(basePage);
                    }
                });
    }
}
