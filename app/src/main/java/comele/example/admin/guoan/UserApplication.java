package comele.example.admin.guoan;

import android.app.Application;

/**
 * Created by admin on 2017/9/25.
 */
public class UserApplication extends Application {

    private static UserApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static UserApplication getApplication() {
        return mInstance;
    }
}
