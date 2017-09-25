package com.kklv.fragme.contract;

import com.kklv.fragme.mvp.IModel;
import com.kklv.fragme.mvp.IPresenter;
import com.kklv.fragme.mvp.IView;

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
