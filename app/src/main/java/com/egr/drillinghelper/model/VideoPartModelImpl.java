package com.egr.drillinghelper.model;

import com.egr.drillinghelper.api.NetApi;
import com.egr.drillinghelper.api.error.EObserver;
import com.egr.drillinghelper.api.error.ResponseThrowable;
import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.bean.response.Video;
import com.egr.drillinghelper.contract.VideoPartContract;
import com.egr.drillinghelper.factory.APIServiceFactory;
import com.egr.drillinghelper.factory.TransformersFactory;
import com.egr.drillinghelper.mvp.BaseMVPFragment;
import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.VideoPartPresenterImpl;

import io.reactivex.annotations.NonNull;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class VideoPartModelImpl extends BaseModel<VideoPartPresenterImpl>
        implements VideoPartContract.Model {
    private NetApi api;

    public VideoPartModelImpl(VideoPartPresenterImpl homePresenter) {
        super(homePresenter);
        api = APIServiceFactory.getInstance().createService();
    }

    @Override
    public void getVideoList(int current) {
        api.getVideoList(current+"")
                .compose(TransformersFactory.<BasePage<Video>>commonTransformer((BaseMVPFragment) presenter.getView()))
                .subscribe(new EObserver<BasePage<Video>>() {
                    @Override
                    public void onError(ResponseThrowable e, String eMsg) {
                        presenter.getView().getVideoListFail(eMsg);
                    }

                    @Override
                    public void onComplete(@NonNull BasePage<Video> data) {
                        presenter.getVideoListSuc(data);
                    }
                });
    }
}
