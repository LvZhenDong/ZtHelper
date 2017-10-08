package com.egr.drillinghelper.api.error;

import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.NullBodyResponse;

import io.reactivex.functions.Function;

/**
 * Created by lvzhendong on 2017/10/8.
 */

public class NullResponseFuc implements Function<BaseResponseBean<NullBodyResponse>, NullBodyResponse> {
    @Override
    public NullBodyResponse apply(BaseResponseBean<NullBodyResponse> response) throws Exception {
        //response中code码不为0 出现错误
        if (!response.isSuccess()) {
            String message = response.getMessage() != null ? response.getMessage() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        return new NullBodyResponse();
    }
}
