package comele.example.admin.guoan.bean;

/**
 * Created by admin on 2017/9/27.
 */
public class ResponseCode {


    /**
     * 网络请求成功
     */
    public static final int REQUEST_SUCCESS = 0;

    /**
     * 业务处理失败
     */
    public static final int REQUEST_PROC_FAIL = 1;

    /**
     * token状态异常
     */
    public static final int STATE_EXCEPTION = 400;

    /**
     * 用户重复登录
     */
    public static final int DUPLICATE_LOGIN= 401;


    /**
     * 网络请求超时
     */
    public static final int REQUEST_TIMEOUT= 100;

    /**
     * 网络不可用
     */
    public static final int NERWORK_NOT_AVAILABLE = 205;

    /**
     * 请求网络失败
     */
    public static final int REQUEST_FAILED = 404;


}
