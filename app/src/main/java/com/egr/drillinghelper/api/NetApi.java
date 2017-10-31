package com.egr.drillinghelper.api;

import com.egr.drillinghelper.bean.base.BasePage;
import com.egr.drillinghelper.bean.base.BaseResponseBean;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.bean.response.ContactUs;
import com.egr.drillinghelper.bean.response.Explain;
import com.egr.drillinghelper.bean.response.ExplainCatalog;
import com.egr.drillinghelper.bean.response.Feedback;
import com.egr.drillinghelper.bean.response.FeedbackDetail;
import com.egr.drillinghelper.bean.response.KnowCatalog;
import com.egr.drillinghelper.bean.response.Message;
import com.egr.drillinghelper.bean.response.NullBodyResponse;
import com.egr.drillinghelper.bean.response.Reply;
import com.egr.drillinghelper.bean.response.ServiceMsg;
import com.egr.drillinghelper.bean.response.Share;
import com.egr.drillinghelper.bean.response.Store;
import com.egr.drillinghelper.bean.response.StoreMore;
import com.egr.drillinghelper.bean.response.UserInfo;
import com.egr.drillinghelper.bean.response.Video;

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
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface NetApi {
    //版本更新接口
    String Version = "version/check?code=";

    /**
     * 登录
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponseBean<UserInfo>> login(@FieldMap HashMap<String, Object> options);


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
     * 进入商城链接
     *
     * @return
     */
    @GET("store/more")
    Observable<BaseResponseBean<StoreMore>> getStoreMore();

    /**
     * 配件列表
     *
     * @param
     * @return
     */
    @GET("store/list")
    Observable<BaseResponseBean<BasePage<Store>>> storeList(@Query("keyword") String keyword,
                                                            @Query("current") String current);

    /**
     * 配件列表Cache
     *
     * @param
     * @return
     */
    @GET("store/list")
    Observable<BaseResponseBean<BasePage<Store>>> storeListCache(@Query("keyword") String keyword,
                                                                 @Query("current") String current,
                                                                 @Query("size") String pageSize);

    /**
     * 修改个人头像
     *
     * @param photo
     * @return
     */
    @Multipart
    @POST("user/photo")
    Observable<BaseResponseBean<NullBodyResponse>> userPhoto(@PartMap Map<String, RequestBody> photo);

    /**
     * 获取说明书cache
     *
     * @return
     */
    @GET("cache/getCache")
    Observable<BaseResponseBean<List<Explain>>> getExplainCache();

    /**
     * 说明书列表
     *
     * @param current
     * @return
     */
    @GET("explain/getList")
    Observable<BaseResponseBean<BasePage<Explain>>> explainList(@Query("keyword") String keyword,
                                                                @Query("current") String current);

    /**
     * 说明书目录
     *
     * @param id
     * @return
     */
    @GET("explain/getById")
    Observable<BaseResponseBean<List<ExplainCatalog>>> explainCatalog(@Query("id") String id);

    @GET("cache/getKnowledgeCache")
    Observable<BaseResponseBean<List<Explain>>> getKnowCache();

    /**
     * 知识问答列表
     *
     * @param current
     * @return
     */
    @GET("know/list")
    Observable<BaseResponseBean<BasePage<Explain>>> knowList(@Query("keyword") String keyword,
                                                             @Query("current") String current);

    /**
     * 知识问答目录
     *
     * @param id
     * @return
     */
    @GET("know/knowList")
    Observable<BaseResponseBean<List<KnowCatalog>>> knowCatalog(@Query("id") String id);

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @GET("explain/getArticle")
    Observable<BaseResponseBean<Article>> getArticle(@Query("articleId") String id);

    /**
     * 退出登录
     *
     * @return
     */
    @DELETE("user/logout")
    Observable<BaseResponseBean<NullBodyResponse>> logout();

    /**
     * 关于EGR
     *
     * @return
     */
    @GET("about/detail")
    Observable<BaseResponseBean<String>> getAbout();

    /**
     * 查询常见问题
     *
     * @return
     */
    @GET("feedback/getList")
    Observable<BaseResponseBean<List<Feedback>>> getFeedbackList();

    /**
     * 常见问题详情
     *
     * @param id
     * @return
     */
    @GET("feedback/getById")
    Observable<BaseResponseBean<FeedbackDetail>> getFeedbackDetail(@Query("id") String id);

    /**
     * 历史反馈
     *
     * @param status
     * @return
     */
    @GET("feedback/list")
    Observable<BaseResponseBean<BasePage<Reply>>> getReplyList(@Query("status") String status,
                                                               @Query("current") String current);

    /**
     * 信息反馈无图
     *
     * @param id
     * @return
     */
    @POST("feedback/save")
    Observable<BaseResponseBean<List<Feedback>>> createFeedback(@Query("question") String id);

    /**
     * 信息反馈有图
     *
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
     *
     * @param keyword
     * @return
     */
    @GET("search/know")
    Observable<BaseResponseBean<BasePage<KnowCatalog>>> searchKnow(@Query("keyword") String keyword,
                                                                   @Query("current") String current);

    /**
     * 搜索使用说明
     *
     * @param keyword
     * @return
     */
    @GET("search/explain")
    Observable<BaseResponseBean<BasePage<Explain>>> searchExplain(@Query("keyword") String keyword,
                                                                  @Query("current") String current);

    /**
     * 搜索配件
     *
     * @param keyword
     * @return
     */
    @GET("search/product")
    Observable<BaseResponseBean<BasePage<Store>>> searchProduct(@Query("keyword") String keyword,
                                                                @Query("current") String current);

    /**
     * 获取消息列表
     */
    @GET("message/list")
    Observable<BaseResponseBean<BasePage<Message>>> getMsgList(@Query("current") String current);

    @GET("message/detail")
    Observable<BaseResponseBean<Message>> getMsgDetail(@Query("messageId") String id);

    /**
     * 获取未读消息数
     *
     * @return
     */
    @GET("message/noRead")
    Observable<BaseResponseBean<Integer>> getNoReadMsg();

    /**
     * 删指定消息
     *
     * @param id
     * @return
     */
    @DELETE("message/delete")
    Observable<BaseResponseBean<NullBodyResponse>> deleteMsg(@Query("messageIds") String id);

    /**
     * 将消息标记为已读
     *
     * @param id
     * @return
     */
    @PUT("message/read")
    Observable<BaseResponseBean<NullBodyResponse>> readMsg(@Query("messageIds") String id); //多条用,隔开

    /**
     * 获取分享内容
     *
     * @return
     */
    @GET("about/share")
    Observable<BaseResponseBean<Share>> share();

    /**
     * 发送服务支持消息
     *
     * @param msg
     * @return
     */
    @POST("support/sendMessage")
    Observable<BaseResponseBean<NullBodyResponse>> sendServiceMsg(@Query("message") String msg);

    /**
     * 发送服务支持图片
     *
     * @param photo
     * @return
     */
    @Multipart
    @POST("support/sendMessage")
    Observable<BaseResponseBean<NullBodyResponse>> sendServiceMsg(@PartMap Map<String, RequestBody> photo);

    /**
     * 获取服务支持历史数据
     *
     * @param current
     * @param size
     * @return
     */
    @GET("support/getMessage")
    Observable<BaseResponseBean<BasePage<ServiceMsg>>> getServiceMsg(@Query("current") String current,
                                                                     @Query("size") String size);

    @GET("support/checkRead")
    Observable<BaseResponseBean<Boolean>> checkRead();

    /**
     * 获取视频区list
     *
     * @param current
     * @return
     */
    @GET("video/getList")
    Observable<BaseResponseBean<BasePage<Video>>> getVideoList(@Query("keyword") String keyword,
                                                               @Query("current") String current);
}
