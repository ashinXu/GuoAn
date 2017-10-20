package comele.example.admin.guoan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.bean.ResponseBean;
import comele.example.admin.guoan.network.AccountRequest;
import comele.example.admin.guoan.utlis.CommonUtil;

/**
 * Created by admin on 2017/9/25.
 * 登录界面
 */
public class RegisterAccountFirstAcitivty extends AppCompatActivity {

    private static final String TAG_GET_VERIFY_CODE = "RegisterAccountFirstAcitivty_get_verify_code";
    private EditText mEtPhone;
    private TextView mTvNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_first);

        EventBus.getDefault().register(this);

        initView();

    }

    private void initView() {

        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mTvNext = (TextView) findViewById(R.id.tv_next);


        mTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mEtPhone.getText().toString().trim();
                AccountRequest.getVerifyCode(phone, TAG_GET_VERIFY_CODE);
            }
        });

    }


    //获取首页新闻数据
    @Subscriber(tag = TAG_GET_VERIFY_CODE)
    private void onGetVerifyCode(ResponseBean.NormalResult result) {
        if (result != null) {
            String success = result.success;
            CommonUtil.showToast(success, 1000);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }


}
