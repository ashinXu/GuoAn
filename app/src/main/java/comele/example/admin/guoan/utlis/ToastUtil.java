package comele.example.admin.guoan.utlis;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by ashin on 2017/10/18.
 */
public class ToastUtil {


    private static Toast mToast;

    public static void show(Context mContext, String message, int time) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (mToast == null) {
            mToast = new Toast(mContext);
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setText(message);

        mToast.show();


    }


}
