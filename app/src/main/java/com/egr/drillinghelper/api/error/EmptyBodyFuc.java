package com.egr.drillinghelper.api.error;

import com.egr.drillinghelper.bean.base.BaseResponseBean;

import io.reactivex.functions.Function;

/**
 *
 *
 * @author LvZhenDong
 *          created on 2017/11/2 14:27
 */
public class EmptyBodyFuc implements Function<BaseResponseBean,Boolean>{
    @Override
    public Boolean apply(BaseResponseBean response) throws Exception {
        //response中code码不为0 出现错误
        if (!response.isSuccess()) {
            String message = response.getMessage() != null ? response.getMessage() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        return true;
    }
}
