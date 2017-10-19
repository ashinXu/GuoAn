package comele.example.admin.guoan.bean;

/**
 * Created by admin on 2017/9/27.
 */
public class ResponseBean {

    /**
     * 从服务器返回的最基本的结果
     */
    public static class BaseResult {

        public static final int SUCCESS = 0;  //成功，默认0代表成功

        public int result_code;

        public String result_msg;
    }

    public static class Cover {
        public String createTime;
        public String description;
        public int id;
        public String modifyTime;
        public String picUrl;
        public String sliderCode;
        public int status;
        public String urlTarget;


        @Override
        public String toString() {
            return "Cover{" +
                    "createTime='" + createTime + '\'' +
                    ", description='" + description + '\'' +
                    ", id=" + id +
                    ", modifyTime='" + modifyTime + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", sliderCode='" + sliderCode + '\'' +
                    ", status=" + status +
                    ", urlTarget='" + urlTarget + '\'' +
                    '}';
        }
    }

    public static class NewsEntity {
        public String extraData;
        public String photo;
        public String title;
        public String updateTime;
        public int id;
        public int type;
    }


}
