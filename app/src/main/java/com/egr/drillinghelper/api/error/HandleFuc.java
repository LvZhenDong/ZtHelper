package com.egr.drillinghelper.api.error;


import com.egr.drillinghelper.bean.base.BaseResponseBean;

import io.reactivex.functions.Function;


/**
 * 对请求的返回结果进行分析(转换map)
 *
 * @param <T>
 * @author Ymmmsick
 * @date 2017-05-12 16:58:10
 */
public class HandleFuc<T> implements Function<BaseResponseBean<T>, T> {
    @Override
    public T apply(BaseResponseBean<T> response) throws Exception {

        if (!response.isSuccess()) {
            String message = response.getMessage() != null ? response.getMessage() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        return response.getData();
    }
}