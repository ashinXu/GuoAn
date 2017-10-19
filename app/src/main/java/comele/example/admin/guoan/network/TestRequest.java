
package comele.example.admin.guoan.network;

/**
 *
 */
public class TestRequest {


  /*  public static void uploadGroupImage(List<String> fileList, final String tag) {
        final ResponseBean.UploadGroupImageResult event = new ResponseBean.UploadGroupImageResult();
        if (!CommonUtil.isNetworkAvailable()) {
            //CommonUtil.showToast(R.string.network_not_available, Toast.LENGTH_LONG);
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<ResponseBean.UploadGroupImageResult> cb = new RequestCallBack<ResponseBean.UploadGroupImageResult>() {
            @Override
            public void onSuccess(Call<ResponseBean.UploadGroupImageResult> call, Response<ResponseBean.UploadGroupImageResult> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<ResponseBean.UploadGroupImageResult> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.BusinessGroupInterface businessGroupInterface = RequestBuilder.getInstance().build(RequestInterface.BusinessGroupInterface.class);
        HashMap<String, RequestBody> partMap = new HashMap<>();
        File file = new File(fileList.get(0));
        RequestBody fileBody = RequestBody.create(MediaType.parse("image*//*"), file);
        partMap.put("logoImage\"; filename=\"" + file.getName() + "\"", fileBody);

        file = new File(fileList.get(1));
        fileBody = RequestBody.create(MediaType.parse("image*//*"), file);
        partMap.put("certificateImage\"; filename=\"" + file.getName() + "\"", fileBody);

        file = new File(fileList.get(2));
        fileBody = RequestBody.create(MediaType.parse("image*//*"), file);
        partMap.put("idPositiveImage\"; filename=\"" + file.getName() + "\"", fileBody);

        file = new File(fileList.get(3));
        fileBody = RequestBody.create(MediaType.parse("image*//*"), file);
        partMap.put("idNegativeImage\"; filename=\"" + file.getName() + "\"", fileBody);

        Call<ResponseBean.UploadGroupImageResult> call = businessGroupInterface.upload_group_image(RequestBuilder.getInstance().buildMultiBody(partMap));
        call.enqueue(cb);
    }*/



  /*  public static void getNews(int group_id, final String tag) {
        final ResponseBean.BaseResult event = new ResponseBean.BaseResult();
        if (!CommonUtil.isNetworkAvailable()) {
            //CommonUtil.showToast(R.string.network_not_available, Toast.LENGTH_LONG);
            event.result_code = ResponseCode.NERWORK_NOT_AVAILABLE;
            EventBus.getDefault().post(event, tag);
            return;
        }
        RequestCallBack<ResponseBean.BaseResult> cb = new RequestCallBack<ResponseBean.BaseResult>() {
            @Override
            public void onSuccess(Call<ResponseBean.BaseResult> call, Response<ResponseBean.BaseResult> response) {
                EventBus.getDefault().post(response.body(), tag);
            }

            @Override
            public void onFail(Call<ResponseBean.BaseResult> call, Throwable t) {
                event.result_code = ResponseCode.REQUEST_FAILED;
                EventBus.getDefault().post(event, tag);
            }
        };

        RequestInterface.CollectionInterface anInterface = RequestBuilder.getInstance().build(RequestInterface.CollectionInterface.class);
        HashMap<String, Object> paraMap = new HashMap();
        paraMap.put("group_id", group_id);
        Call<ResponseBean.BaseResult> call = anInterface.cancel_collection(RequestBuilder.getInstance().buildBody(paraMap));
        call.enqueue(cb);
    }*/

}
