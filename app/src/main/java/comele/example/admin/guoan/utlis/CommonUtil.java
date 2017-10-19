package comele.example.admin.guoan.utlis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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


}
