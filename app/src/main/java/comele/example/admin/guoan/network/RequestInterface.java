package comele.example.admin.guoan.network;

import java.util.List;

import comele.example.admin.guoan.bean.ResponseBean;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/9/27.
 */
public class RequestInterface {


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


        /**
         * 新闻点赞
         */
        @POST("i/news/addNewsPraise")
        Call<Boolean> praiseNews(@Query("newsId") int newsId);

    }


    public interface AccountInterface {


        /**
         * 获取验证码
         */
        @POST("i/passport/activeCode/{phoneNum}")
        Call<ResponseBean.NormalResult> getVerifyCode(@Path("phoneNum") String phoneNum);


        /**
         * 用户注册
         */
        @POST("i/passport/reg/{activeCode}/{phoneNum}/{password}/{regSource}")
        Call<ResponseBean.NormalResult> userRegister(@Path("activeCode") int activeCode, @Path("phoneNum") String phoneNum, @Path("password") String password, @Path("regSource") String regSource);
    }

    public interface UploadImageInterface {

        @Multipart
        @POST("i/news/uploadImg")
        Call<String> updateAvatar(@Part("file\"; filename=\"image.png\"") RequestBody file);
    }


}
