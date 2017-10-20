package comele.example.admin.guoan.network;

import java.util.HashMap;
import java.util.List;

import comele.example.admin.guoan.bean.ResponseBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/9/27.
 */
public class RequestInterface {

    /**
     * 收藏接口
     */
    public interface CollectionInterface {
        /**
         * 收藏内容
         */
        @POST("?c=user&m=collect_resources")
        Call<ResponseBean.BaseResult> collect_resources(@Body HashMap<String, Object> body);

        /**
         * 取消收藏内容
         */
        @POST("?c=user&m=cancel_collection")
        Call<ResponseBean.BaseResult> cancel_collection(@Body HashMap<String, Object> body);

    }


    public interface CoverInterface {

        /**
         * 广告墙
         */
        @POST("i/slider/details/get/")
        Call<List<ResponseBean.Cover>> getCoverList();


        /**
         * 新闻列表
         */
        @POST("i/news/queryIndexData?")
        Call<List<ResponseBean.NewsEntity>> getNewsList(@Query("offset") int offset, @Query("rows") int rows);


        /**
         * 新闻详情
         */
        @POST("i/news/toArticle/{id}")
        Call<String> getNewsDetail(@Path("id") int id);

    }


    public interface AccountInterface {


        /**
         * 获取验证码
         */
        @POST("i/passport/activeCode/{phoneNum}")
        Call<ResponseBean.NormalResult> getVerifyCode(@Path("phoneNum") String phoneNum);
    }


}
