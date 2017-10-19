package comele.example.admin.guoan.network;

import java.util.concurrent.TimeUnit;

import comele.example.admin.guoan.utlis.ConfigUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/9/27.
 */
public class RequestBuilder {

    private static final String API_URL = ConfigUtil.getApiUrl();


    private static RequestBuilder ourInstance = new RequestBuilder();
    private Retrofit mRetrofit;

    public static RequestBuilder getInstance() {
        return ourInstance;
    }

    private RequestBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS);

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    /**
     * 使用泛型来创建不同的网络请求接口
     *
     * @param request 网络请求接口的class类型
     * @param <T>     泛型
     * @return T类型网络接口
     */
    public <T> T build(Class<T> request) {
        return mRetrofit.create(request);
    }

   /* private HashMap<String, Object> buildHeader() {

        YunMoFangProviderCenter providerCenter = ProviderFactory.getYunMoFangProviderCenter();
        DeviceInfo deviceInfo = providerCenter.getDeviceInfo(UserApplication.getApplication());

        HashMap<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("imei", deviceInfo.imei);
        headerMap.put("mac", deviceInfo.mac);
        headerMap.put("platform", deviceInfo.platform);
        headerMap.put("model", deviceInfo.model);
        headerMap.put("version", deviceInfo.version);
        headerMap.put("country", deviceInfo.country);
        headerMap.put("language", deviceInfo.language);
        headerMap.put("network", deviceInfo.network);
        headerMap.put("timestamp", deviceInfo.timestamp);
        headerMap.put("random", deviceInfo.random);
        headerMap.put("device_id", deviceInfo.device_id);
        headerMap.put("app_version", deviceInfo.app_version);

        if (YunPianApplication.getApplication().getAccount().isLoggedIn()) {
            headerMap.put("eid", deviceInfo.eid);
            headerMap.put("signature", deviceInfo.signature);
        }

        return headerMap;
    }*/


   /* public HashMap<String, Object> buildBody(HashMap<String, Object> paraMap) {

        HashMap<String, Object> requestMap = new HashMap<String, Object>();
        HashMap<String, Object> headerMap = buildHeader();

        requestMap.put("yy_header", headerMap);
        if (null == paraMap) {
            paraMap = new HashMap<String, Object>();
        }
        requestMap.put("yy_body", paraMap);

        return requestMap;
    }


    public HashMap<String, RequestBody> buildMultiBody(HashMap<String, RequestBody> partMap) {
        HashMap<String, Object> headerMap = buildHeader();

        RequestBody requestBody;
        for (String key : headerMap.keySet()) {
            requestBody = RequestBody.create(MediaType.parse("text/plain"), "" + headerMap.get(key));
            partMap.put(key, requestBody);
        }

        return partMap;
    }*/


}
