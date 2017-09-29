package com.egr.drillinghelper.model;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.contract.PartsContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.PartsPresenterImpl;
import com.egr.drillinghelper.presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import static android.R.id.list;
import static com.egr.drillinghelper.factory.TransformersFactory.commonTransformer;
import static com.pgyersdk.c.a.e;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class PartsModelImpl extends BaseModel<PartsPresenterImpl> implements PartsContract.Model{

    private NetApi api;

    public PartsModelImpl(PartsPresenterImpl presenter) {
        super(presenter);
        api = APIServiceFactory.getInstance().createService(NetApi.class);
    }

    @Override
    public void getPartsList(int current) {
        HashMap<String,Object> options=new HashMap<>();
        options.put("current",current);
        api.storeList(options)
                .compose(TransformersFactory.<Store>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<Store>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {

                        if(eMsg.equals("未知错误")){
                            presenter.getView().getPastsFail(getContext().getString(R.string.no_more_data));
                        }else {
                            presenter.getView().getPastsFail(eMsg);
                        }
                    }

                    @Override
                    public void onComplete(@NonNull Store list) {
                        presenter.getPartsListSuccess(list);
                    }
                });
    }
}
