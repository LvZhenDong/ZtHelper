package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

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
