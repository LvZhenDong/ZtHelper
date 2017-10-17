package com.egr.drillinghelper.model;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.PartsPresenterImpl;
import com.egr.drillinghelper.ui.adapter.PartsAdapter;

import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class PartsModelImpl extends BaseModel<PartsPresenterImpl> implements PartsContract.Model {

    StoreMore more;
    private NetApi api;

    public PartsModelImpl(PartsPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getPartsList(int current) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("current", current);
        api.storeList(options)
                .compose(TransformersFactory.<Store>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<Store>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getPastsFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull Store data) {
                        int current = data.getCurrent();
                        if (current == 1 && more != null) {
                            Store.RecordsBean mall = new Store.RecordsBean();
                            mall.setUrl(more.getUrl());
                            mall.setName(getContext().getString(R.string.mall));
                            mall.setId(PartsAdapter.INTO_MALL);
                            List<Store.RecordsBean> list = data.getRecords();
                            if (list != null)
                                list.add(0, mall);
                        }
                        presenter.getPartsListSuccess(data);
                    }
                });
    }

    @Override
    public void getMall() {
        api.getStoreMore()
                .compose(TransformersFactory.<StoreMore>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<StoreMore>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getMallSuccess();
                    }

                    @Override
                    public void onComplete(@NonNull StoreMore data) {
                        more = data;
                        presenter.getMallSuccess();
                    }
                });
    }
}
