package comele.example.admin.guoan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

import comele.example.admin.guoan.R;

/**
 * Created by admin on 2017/9/25.
 * 登录界面
 */
public class RegisterAccountFirstAcitivty extends AppCompatActivity {

    private static final String TAG_GET_DATA = "get_data";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_first);

        EventBus.getDefault().register(this);

        initView();

    }

    private void initView() {

        View viewById1 = findViewById(R.id.et_phone);
        TextView viewById = (TextView) findViewById(R.id.tv_next);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }




}
