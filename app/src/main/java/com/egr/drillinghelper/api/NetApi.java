package com.egr.drillinghelper.api;

import com.egr.drillinghelper.bean.base.BasePageResponse;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.CreateFeedbackResponse;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.ExplainOut;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.bean.response.FeedbackDetail;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.LoginResponse;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.bean.response.Parts;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.bean.response.SearchResult;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static android.R.attr.id;

public interface NetApi {

    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponseBean<LoginResponse>> login(@FieldMap HashMap<String, Object> options);


    /**
     * 注册
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResponseBean<NullBodyResponse>> register(@FieldMap HashMap<String, Object> options);

    /**
     * 忘记密码
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/forget")
    Observable<BaseResponseBean<NullBodyResponse>> forget(@FieldMap HashMap<String, Object> options);

    /**
     * 获取验证码
     *
     * @param type  注册：register
     * @param phone
     * @return
     */
    @GET("common/smscode")
    Observable<BaseResponseBean<String>> getVerCode(@Query("templateType") String type,
                                                    @Query("phone") String phone);

    /**
     * 个人中心数据
     *
     * @return
     */
    @GET("user/info")
    Observable<BaseResponseBean<UserInfo>> userInfo();

    /**
     * 联系我们
     *
     * @return
     */
    @GET("contact/list")
    Observable<BaseResponseBean<ContactUs>> contactList();

    /**
     * 配件列表
     * @param options
     * @return
     */
    @GET("store/list")
    Observable<BaseResponseBean<Store>> storeList(@QueryMap HashMap<String, Object> options);

    /**
     * 修改个人头像
     * @param photo
     * @return
     */
    @Multipart
    @POST("user/photo")
    Observable<BaseResponseBean<NullBodyResponse>> userPhoto(@PartMap Map<String, RequestBody> photo);

    /**
     * 说明书列表
     * @param current
     * @return
     */
    @GET("explain/getList")
    Observable<BaseResponseBean<Explain>> explainList(@Query("current") String current);

    /**
     * 说明书目录
     * @param id
     * @return
     */
    @GET("explain/getById")
    Observable<BaseResponseBean<List<ExplainCatalog>>> explainCatalog(@Query("id")String id);

    /**
     * 知识问答列表
     * @param current
     * @return
     */
    @GET("know/list")
    Observable<BaseResponseBean<Explain>> knowList(@Query("current") String current);

    /**
     * 知识问答目录
     * @param id
     * @return
     */
    @GET("know/knowList")
    Observable<BaseResponseBean<List<KnowCatalog>>> knowCatalog(@Query("id")String id);
    /**
     * 文章详情
     * @param id
     * @return
     */
    @GET("explain/getArticle")
    Observable<BaseResponseBean<Article>> getArticle(@Query("articleId") String id);

    /**
     *  退出登录
     * @return
     */
    @DELETE("user/logout")
    Observable<BaseResponseBean<NullBodyResponse>> logout();

    @GET("about/detail")
    Observable<BaseResponseBean<String>> getAbout();

    /**
     * 查询常见问题
     * @return
     */
    @GET("feedback/getList")
    Observable<BaseResponseBean<List<Feedback>>> getFeedbackList();

    /**
     * 常见问题详情
     * @param id
     * @return
     */
    @GET("feedback/getById")
    Observable<BaseResponseBean<FeedbackDetail>> getFeedbackDetail(@Query("id") String id);

    /**
     * 历史反馈
     * @param status
     * @return
     */
    @GET("feedback/list")
    Observable<BaseResponseBean<Reply>> getReplyList(@Query("status") String status,
                                                     @Query("current") String current);

    /**
     * 信息反馈无图
     * @param id
     * @return
     */
    @POST("feedback/save")
    Observable<BaseResponseBean<List<Feedback>>> createFeedback(@Query("question") String id);

    /**
     * 信息反馈有图
     * @param id
     * @param photo
     * @return
     */
    @Multipart
    @POST("feedback/save")
    Observable<BaseResponseBean<List<Feedback>>> createFeedback(@Query("question") String id,
                                                        @PartMap Map<String, RequestBody> photo);

    /**
     * 搜索知识问答
     * @param keyword
     * @return
     */
    @GET("search/know")
    Observable<BaseResponseBean<BasePageResponse<KnowCatalog>>> searchKnow(@Query("keyword") String keyword,
                                                                           @Query("current") String current);

    /**
     * 搜索使用说明
     * @param keyword
     * @return
     */
    @GET("search/explain")
    Observable<BaseResponseBean<BasePageResponse<ExplainOut>>> searchExplain(@Query("keyword") String keyword,
                                                                             @Query("current") String current);

    /**
     *
     * @param keyword
     * @return
     */
    @GET("search/product")
    Observable<BaseResponseBean<BasePageResponse<Parts>>> searchProduct(@Query("keyword") String keyword,
                                                                        @Query("current") String current);
}
