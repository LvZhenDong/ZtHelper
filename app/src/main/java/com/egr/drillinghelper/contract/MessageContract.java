package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * author 边凌
 * date 2017/9/26 10:05
 * 类描述：
 */

public interface MessageContract {
    interface Model extends IModel {

    }

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {

    }
}
