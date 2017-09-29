package com.egr.drillinghelper.presenter;

import android.text.TextUtils;

import com.egr.drillinghelper.common.RxBusConstant;
import com.egr.drillinghelper.contract.PersonalContract;
import com.egr.drillinghelper.model.PersonalModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.EgrRxBus;

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

public class PersonalPresenterImpl extends BasePresenter<PersonalContract.View,
        PersonalModelImpl> implements PersonalContract.Presenter{
    @Override
    protected IModel createModel() {
        return new PersonalModelImpl(this);
    }

    @Override
    public void changeHead(String path) {
        mModel.userPhoto(addImage(path));
    }

    @Override
    public void changeHeadSuccess() {
        EgrRxBus.post(RxBusConstant.UPDATE_MY_USER_INFO);   //更新个人中心
        getView().changeHeadSuccess();
    }

    private Map<String, RequestBody> addImage(String path) {
        Map<String, RequestBody> images = new HashMap<>();
        if (TextUtils.isEmpty(path))
            return null;
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        images.put("photo\";filename=\"" + file.getName() + "\"", requestFile);

        return images;
    }
}
