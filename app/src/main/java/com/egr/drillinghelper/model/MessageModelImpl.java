package com.egr.drillinghelper.model;

import com.egr.drillinghelper.mvp.BaseModel;
import com.egr.drillinghelper.presenter.HomePresenterImpl;
import com.egr.drillinghelper.presenter.MessagePresenterImpl;

/**
 * author lzd
 * date 2017/9/26 16:40
 * 类描述：
 */

public class MessageModelImpl extends BaseModel<MessagePresenterImpl> {
    public MessageModelImpl(MessagePresenterImpl presenter) {
        super(presenter);
    }
}
