package comele.example.admin.guoan.network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017/9/27.
 */
public abstract class RequestCallBack<T> implements Callback<T> {

    private static final String TAG = RequestCallBack.class.getSimpleName();

    private boolean canceled;
    private boolean showFailureToast; //当网络请求失败时，是否给用户显示toast

    public RequestCallBack() {
        canceled = false;
        showFailureToast = false;
    }

    public RequestCallBack(boolean showFailureToast) {
        this.showFailureToast = showFailureToast;
    }

    public void cancel() {
        canceled = true;
    }

    public void isShowFailureToast(boolean showToast) {
        showFailureToast = showToast;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!canceled) {
          /*  if (AccountManager.checkToken((ResponseBean.BaseResult) response.body())) {
                onSuccess(call, response);
            }*/

            onSuccess(call, response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        if (showFailureToast) {
            //CommonUtil.showToast(R.string.network_error, Toast.LENGTH_LONG);
        }
        if (!canceled) {
            onFail(call, t);
        }
    }

    protected abstract void onSuccess(Call<T> call, Response<T> response);

    /**
     * 子类可重写此方法实现对网络请求错误的处理
     */
    public void onFail(Call<T> call, Throwable t) {
        Log.e(TAG, "error=" + t.getMessage());
    }
}
