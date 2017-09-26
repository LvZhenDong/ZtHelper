package com.egr.drillinghelper.contract;

import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.mvp.IPresenter;
import com.egr.drillinghelper.mvp.IView;

/**
 * Created by lvzhendong on 2017/9/25.
 */

public interface TextContract {
    interface Model extends IModel{

    }

    interface View extends IView{

    }

    interface Presendter extends IPresenter<View>{

    }
}
