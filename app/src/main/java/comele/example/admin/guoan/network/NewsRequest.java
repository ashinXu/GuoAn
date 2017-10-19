package comele.example.admin.guoan.network;

import org.simple.eventbus.EventBus;

import java.util.List;

import comele.example.admin.guoan.bean.ResponseBean;
import comele.example.admin.guoan.bean.ResponseCode;
import comele.example.admin.guoan.utlis.CommonUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by admin on 2017/9/28.
 */
public class NewsRequest {

    /**
     * 获取首页的广告图
     *
     * @param tag
     */
    public static void getCover(final String tag) {

        final ResponseBean.BaseResult event = new ResponseBean.BaseResult();

        if (!CommonUtil.isNetworkAvailable()) {
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<List<ResponseBean.Cover>> cb = new RequestCallBack<List<ResponseBean.Cover>>() {
            @Override
            public void onSuccess(Call<List<ResponseBean.Cover>> call, Response<List<ResponseBean.Cover>> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<List<ResponseBean.Cover>> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.CoverInterface coverInterface = RequestBuilder.getInstance().build(RequestInterface.CoverInterface.class);
        Call<List<ResponseBean.Cover>> call = coverInterface.getCoverList();
        call.enqueue(cb);

    }


    /**
     * 获取首页的新闻列表
     *
     * @param tag
     */
    public static void getNewsList(int offset, int rows, final String tag) {

        final ResponseBean.BaseResult event = new ResponseBean.BaseResult();

        if (!CommonUtil.isNetworkAvailable()) {
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<List<ResponseBean.NewsEntity>> cb = new RequestCallBack<List<ResponseBean.NewsEntity>>() {
            @Override
            public void onSuccess(Call<List<ResponseBean.NewsEntity>> call, Response<List<ResponseBean.NewsEntity>> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<List<ResponseBean.NewsEntity>> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.CoverInterface coverInterface = RequestBuilder.getInstance().build(RequestInterface.CoverInterface.class);
        Call<List<ResponseBean.NewsEntity>> call = coverInterface.getNewsList(offset, rows);
        call.enqueue(cb);

    }


    /**
     * 获取首页的新闻列表
     *
     * @param tag
     */
    public static void getNewsDetail(int id, final String tag) {

        final ResponseBean.BaseResult event = new ResponseBean.BaseResult();

        if (!CommonUtil.isNetworkAvailable()) {
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<String> cb = new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call<String> call, Response<String> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<String> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.CoverInterface coverInterface = RequestBuilder.getInstance().build(RequestInterface.CoverInterface.class);
        Call<String> call = coverInterface.getNewsDetail(id);
        call.enqueue(cb);

    }


    /**
     * 新闻点赞
     *
     * @param tag
     */
    public static void praiseNews(int id, final String tag) {

        final ResponseBean.BaseResult event = new ResponseBean.BaseResult();

        if (!CommonUtil.isNetworkAvailable()) {
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<String> cb = new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call<String> call, Response<String> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<String> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.CoverInterface coverInterface = RequestBuilder.getInstance().build(RequestInterface.CoverInterface.class);
        Call<String> call = coverInterface.getNewsDetail(id);
        call.enqueue(cb);

    }


}
