package comele.example.admin.guoan.network;

import org.simple.eventbus.EventBus;

import comele.example.admin.guoan.bean.ResponseBean;
import comele.example.admin.guoan.bean.ResponseCode;
import comele.example.admin.guoan.utlis.CommonUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by admin on 2017/9/28.
 * 用户接口
 */
public class AccountRequest {

    /**
     * 获取验证码
     *
     * @param tag
     */
    public static void getVerifyCode(String phone, final String tag) {

        final ResponseBean.BaseResult event = new ResponseBean.BaseResult();

        if (!CommonUtil.isNetworkAvailable()) {
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<ResponseBean.NormalResult> cb = new RequestCallBack<ResponseBean.NormalResult>() {
            @Override
            public void onSuccess(Call<ResponseBean.NormalResult> call, Response<ResponseBean.NormalResult> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<ResponseBean.NormalResult> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.AccountInterface accountInterface = RequestBuilder.getInstance().build(RequestInterface.AccountInterface.class);
        Call<ResponseBean.NormalResult> call = accountInterface.getVerifyCode(phone);
        call.enqueue(cb);

    }


}
