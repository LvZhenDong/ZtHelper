package com.egr.drillinghelper.presenter;

import android.text.TextUtils;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.contract.ServiceContract;
import com.egr.drillinghelper.model.ServiceModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class ServicePresenterImpl extends BasePresenter<ServiceContract.View,
        ServiceModelImpl> implements ServiceContract.Presenter {
    int current;

    @Override
    protected IModel createModel() {
        return new ServiceModelImpl(this);
    }

    @Override
    public void sendMsg(String msg) {
        mModel.sendMsg(msg);
    }

    @Override
    public void getMsgSuc(BasePage<ServiceMsg> data) {
        if (CollectionUtil.isListEmpty(data.getRecords()) && data.getCurrent() != 1) {
            getView().noMoreData();
        } else {
            current = data.getCurrent();
            getView().getMsgSuc(data);
        }
    }

    @Override
    public void sendPhoto(String path) {
        mModel.sendPhoto(addImage(path));
    }

    @Override
    public void getMsg() {
        current = 1;
        mModel.getMsg(current,20);
    }

    @Override
    public void loadMore() {
        mModel.getMsg(current + 1,20);
    }

    @Override
    public void getLatestMsg() {
        mModel.getLatest();
    }

    @Override
    public void resolved(String id) {
        mModel.resolved(id);
    }

    @Override
    public void unsolved(String id) {
        mModel.unsolved(id);
    }

    private Map<String, RequestBody> addImage(String path) {
        Map<String, RequestBody> images = new HashMap<>();
        if (TextUtils.isEmpty(path))
            return null;
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        images.put("files\";filename=\"" + file.getName() + "\"", requestFile);

        return images;
    }
}
