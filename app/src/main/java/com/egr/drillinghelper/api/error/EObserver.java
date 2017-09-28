package com.egr.drillinghelper.api.error;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * author lzd
 * date 2017/9/28 11:25
 * 类描述：
 */

public abstract class EObserver<T>  implements Observer<T> {
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ResponseThrowable) {
            onError((ResponseThrowable) e,((ResponseThrowable) e).getLMessage());
        } else {
            onError(new ResponseThrowable(e, ERROR.UNKNOWN),e.getMessage());
        }
    }

    public abstract void onError(ResponseThrowable e,String eMsg);

    @Override
    public void onNext(@NonNull T t) {
        onComplete(t);
    }

    public abstract void onComplete(@NonNull T t);

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
