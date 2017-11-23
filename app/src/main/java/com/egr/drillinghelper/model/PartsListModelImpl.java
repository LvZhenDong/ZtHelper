package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.contract.PartsListContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.PartsListPresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;

import io.reactivex.annotations.NonNull;


public class PartsListModelImpl extends BaseModel<PartsListPresenterImpl> implements PartsListContract.Model {
    private NetApi api;

    public PartsListModelImpl(PartsListPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getPartsList(String type, String keyword, int pageNum) {
            api.storeList(type,keyword, pageNum + "")
                    .compose(TransformersFactory.<BasePage<Store>>commonTransformer((BaseMVPActivity) presenter.getView()))
                    .subscribe(new EObserver<BasePage<Store>>() {
                        @Override
                        public void onError(ResponseThrowable e, String eMsg) {
                            presenter.getView().getPastsFail(eMsg);
                        }

                        @Override
                        public void onComplete(@NonNull BasePage<Store> data) {
                            presenter.getPartsListSuccess(data);
                        }
                    });

    }
}