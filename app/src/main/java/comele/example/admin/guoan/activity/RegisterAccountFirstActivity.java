package comele.example.admin.guoan.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.utlis.CommonUtil;
import comele.example.admin.guoan.utlis.ConstUtil;

/**
 * Created by admin on 2017/9/25.
 * 登录界面
 */
public class RegisterAccountFirstActivity extends AppCompatActivity {


    private EditText mEtPhone;
    private TextView mTvNext;
    private CheckBox mCbProtocol;
    private Dialog mDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_first);

        EventBus.getDefault().register(this);

        initView();

    }

    private void initView() {

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("新用户注册");

        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mTvNext = (TextView) findViewById(R.id.tv_next);

        mCbProtocol = (CheckBox) findViewById(R.id.cb_protocol);


        mTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //验证手机号
                String phone = mEtPhone.getText().toString().trim();
                if (!CommonUtil.isPhone(phone)) {
                    CommonUtil.showToast("请输入正确的手机号码", 1000);
                    return;
                }

                if (!mCbProtocol.isChecked()) {
                    CommonUtil.showToast("尚未同意用户注册协议", 1000);
                    return;
                }

                //显示对话框
                showDialog(phone);
            }
        });

    }


    /**
     * 显示倒计时对话框
     */
    private void showDialog(final String phone) {
        if (mDialog == null) {
            mDialog = new Dialog(this, R.style.FullHeightDialog);
            mDialog.setContentView(R.layout.dialog_send_code);
            Window dialogWindow = mDialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.CENTER);
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度

            dialogWindow.setAttributes(lp);
            mDialog.setCanceledOnTouchOutside(false);

            TextView tvPhone = (TextView) mDialog.findViewById(R.id.tv_phone);
            tvPhone.setText(phone);

            mDialog.findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    Intent intent = new Intent(RegisterAccountFirstActivity.this, RegisterAccountSecondActivity.class);
                    intent.putExtra(ConstUtil.TAG_PHONE,phone);
                    startActivity(intent);

                }
            });

            mDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
        }
        mDialog.show();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }


}
