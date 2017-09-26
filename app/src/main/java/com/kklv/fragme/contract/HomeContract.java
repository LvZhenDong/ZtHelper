package com.kklv.fragme.contract;

import com.kklv.fragme.mvp.IModel;
import com.kklv.fragme.mvp.IPresenter;
import com.kklv.fragme.mvp.IView;

/**
 * author lzd
 * date 2017/9/26 16:39
 * 类描述：
 */

public interface HomeContract {
    interface Model extends IModel {

    }

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {

    }
}
