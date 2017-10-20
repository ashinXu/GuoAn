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
import comele.example.admin.guoan.utlis.ConstUtil;

/**
 * Created by admin on 2017/9/25.
 * 注册界面
 */
public class RegisterAccountSecondActivity extends AppCompatActivity {

    private static final String TAG_GET_VERIFY_CODE = "RegisterAccountSecondActivity_get_verify_code";


    private String mPhone;
    private EditText mEtCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_second);

        EventBus.getDefault().register(this);


        mPhone = getIntent().getStringExtra(ConstUtil.TAG_PHONE);

        initView();

        initData();

    }

    private void initData() {

        AccountRequest.getVerifyCode(mPhone, TAG_GET_VERIFY_CODE);

    }


    /**
     * 获取手机验证码
     *
     * @param result
     */
    @Subscriber(tag = TAG_GET_VERIFY_CODE)
    private void onGetVerifyCode(ResponseBean.NormalResult result) {
        if (result != null) {
            String success = result.success;
            CommonUtil.showToast("发送验证码成功", 1000);
        } else {
            CommonUtil.showToast(result.success, 1000);
        }
    }

    private void initView() {

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("手机快速注册");

        mEtCode = (EditText) findViewById(R.id.et_code);
        TextView tvNext = (TextView) findViewById(R.id.tv_next);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }


}
