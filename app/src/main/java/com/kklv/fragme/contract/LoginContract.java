package com.kklv.fragme.contract;

import com.kklv.fragme.mvp.IModel;
import com.kklv.fragme.mvp.IPresenter;
import com.kklv.fragme.mvp.IView;

/**
 * author 边凌
 * date 2017/9/26 10:05
 * 类描述：
 */

public interface LoginContract {
    interface Model extends IModel {

    }

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {

    }
}
