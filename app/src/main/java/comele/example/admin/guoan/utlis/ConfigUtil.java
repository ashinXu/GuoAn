package comele.example.admin.guoan.utlis;

import comele.example.admin.guoan.BuildConfig;

/**
 * Created by admin on 2017/9/27.
 */
public class ConfigUtil {


    private static String REAL_API_URL = "https://ga.api.49.city/";
    private static String DEBUG_API_URL = "http://test.49city.takeeasy.com.cn:90/ga.api/";

    private static boolean mIsDebug = BuildConfig.DEBUG;

    public static boolean isDebug(){
        return mIsDebug;
    }


    public static String getApiUrl(){
       /* if (mIsDebug){
            return DEBUG_API_URL;
        }*/
        return REAL_API_URL;
    }




}
