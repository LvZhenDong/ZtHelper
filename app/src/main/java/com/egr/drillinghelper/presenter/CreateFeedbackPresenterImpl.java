package com.egr.drillinghelper.presenter;

import android.text.TextUtils;

import com.egr.drillinghelper.contract.CreateFeedbackContract;
import com.egr.drillinghelper.model.CreateFeedbackModelImpl;
import com.egr.drillinghelper.mvp.BasePresenter;
import com.egr.drillinghelper.mvp.IModel;
import com.egr.drillinghelper.utils.CollectionUtil;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.R.attr.path;

/**
 * author lzd
 * date 2017/9/26 16:41
 * 类描述：
 */

public class CreateFeedbackPresenterImpl extends BasePresenter<CreateFeedbackContract.View,
        CreateFeedbackModelImpl> implements CreateFeedbackContract.Presenter{
    @Override
    protected IModel createModel() {
        return new CreateFeedbackModelImpl(this);
    }

    @Override
    public void createFeedback(String question, List<ImageItem> imageItems) {
        if(CollectionUtil.isListEmpty(imageItems)){
            mModel.createFeedback(question);
        }else {
            mModel.createFeedback(question,addImage(imageItems));
        }
    }

    private Map<String, RequestBody> addImage(List<ImageItem> imageItems) {
        Map<String, RequestBody> images = new HashMap<>();

        for (ImageItem item:imageItems) {
            String path=item.path;
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            images.put("files\";filename=\"" + file.getName() + "\"", requestFile);
        }

        return images;
    }
}
