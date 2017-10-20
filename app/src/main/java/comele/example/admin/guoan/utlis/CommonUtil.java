package comele.example.admin.guoan.utlis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.UserApplication;

/**
 * Created by admin on 2017/9/27.
 */
public class CommonUtil {


   /* public static void showToast(String prompt, int time) {
        final Toast toast = makeToast(prompt, time, 17);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                toast.show();
            }
        }, 0L, 1000L);
        (new Timer()).schedule(new TimerTask() {
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, 1000L);
    }*/


    public static void showToast(String prompt, int time) {

        Context context = UserApplication.getApplication().getBaseContext();
        Toast.makeText(context, prompt, Toast.LENGTH_SHORT).show();
    }


    public static boolean isNetworkAvailable() {

        Context context = UserApplication.getApplication().getBaseContext();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; ++i) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

            return false;
        }
    }


    /**
     * 加载图片到到ImageView
     *
     * @param headImgView
     * @param headImgUrl
     * @param headImgName
     */
    public static void loadImage(ImageView headImgView, String headImgUrl, String headImgName) {
        Context context = UserApplication.getApplication();

        Picasso.with(context).load(headImgUrl).placeholder(R.drawable.icon_default_head).error(R.drawable.icon_default_head).into(headImgView);

    }


    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


}
