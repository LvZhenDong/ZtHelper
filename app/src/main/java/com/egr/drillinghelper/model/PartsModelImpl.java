package com.egr.drillinghelper.model;

import android.text.TextUtils;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.PartsPresenterImpl;
import com.egr.drillinghelper.ui.adapter.PartsAdapter;
import com.egr.drillinghelper.utils.CacheUtils;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.egr.drillinghelper.utils.GlideUtils;
import com.egr.drillinghelper.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.egr.drillinghelper.api.error.ERROR.TIMEOUT_ERROR;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class PartsModelImpl extends BaseModel<PartsPresenterImpl> implements PartsContract.Model {

    List<StoreMore> more;
    private NetApi api;

    public PartsModelImpl(PartsPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getPartsList(final String keyword, int current) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            api.storeList(keyword, current + "")
                    .compose(TransformersFactory.<BasePage<Store>>commonTransformer((BaseMVPFragment) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Store>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            if (e.code == TIMEOUT_ERROR)
                                showCache(keyword);
                            else
                                presenter.getPastsFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Store> data) {
                            int current = data.getCurrent();
                            if (current == 1 && !CollectionUtil.isListEmpty(more)) {    //添加商城到list
                                List<Store> list = data.getRecords();
                                for (int i = 0; i < more.size(); i++) {
                                    StoreMore item = more.get(more.size() - i - 1);
                                    Store mall = new Store();
                                    mall.setUrl(item.getUrl());
                                    mall.setName(item.getName());
                                    mall.setInformation(item.getDescription());
                                    mall.setId(PartsAdapter.INTO_MALL);
                                    if (list != null)
                                        list.add(0, mall);
                                }
                            }
                            presenter.getPartsListSuccess(data);
                        }
                    });
        } else {
            showCache(keyword);
        }
    }

    @Override
    public void getMall() {

        api.getStoreMore()
                .compose(TransformersFactory.<List<StoreMore>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<List<StoreMore>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getMallSuccess();
                    }

                    @Override
                    public void onComplete(@NonNull List<StoreMore> data) {
                        more = data;
                        presenter.getMallSuccess();
                    }
                });

    }

    private void showCache(String keyword) {
        try {
            presenter.getView().showParts(searchPartsInCache(keyword));
        } catch (Exception e) {
            presenter.getPastsFail(getContext().getString(R.string.net_error));
        }
    }

    private List<Store> searchPartsInCache(String keyword) throws Exception {
        List<Store> searchResult = new ArrayList<>();
        List<Store> cacheList = CacheUtils.getParts();
        if (TextUtils.isEmpty(keyword) || CollectionUtil.isListEmpty(cacheList)) return cacheList;
        for (Store item : cacheList) {
            String title = item.getName();
            if (TextUtils.isEmpty(title)) continue;
            if (title.toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(item);
            }
        }

        return searchResult;
    }

    @Override
    public void getPartsCache() {
        api.storeListCache("", 1 + "", 100 + "")
                .compose(TransformersFactory.<BasePage<Store>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<BasePage<Store>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                    }

                    @Override
                    public void onComplete(@NonNull BasePage<Store> data) {
                        CacheUtils.saveParts(data.getRecords());
                        saveImg();
                    }
                });
    }

    private void saveImg() {
        try {
            List<Store> stores = CacheUtils.getParts();
            for (Store item : stores) {
                if (!TextUtils.isEmpty(item.getPicture())) {
                    GlideUtils.perLoadImg(getContext(), item.getPicture());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
