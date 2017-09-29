package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * author lzd
 * date 2017/9/26 15:09
 * 类描述：
 */

public interface PersonalContract {
    interface Model extends IModel {
        void userPhoto(Map<String, RequestBody> photo);
    }

    interface View extends IView {
        void changeHeadSuccess();

        void changeHeadFail(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void changeHead(String path);

        void changeHeadSuccess();
    }
}
