package comele.example.admin.guoan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.utlis.CommonUtil;
import comele.example.admin.guoan.utlis.ConstUtil;
import comele.example.admin.guoan.utlis.SharedPrefenceUtil;

/**
 * Created by admin on 2017/9/25.
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_GET_DATA = "get_data";
    private Tencent mTencent;
    private String scope = "all";
    private boolean isServerSideLogin = false;
    private UserInfo mInfo;
    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EventBus.getDefault().register(this);

        initView();

    }

    private void initView() {

        EditText etAccount = (EditText) findViewById(R.id.et_account);

        EditText etPassword = (EditText) findViewById(R.id.et_password);

        TextView tvLogin = (TextView) findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
        TextView tvRegister = (TextView) findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        TextView tvForget = (TextView) findViewById(R.id.tv_forget_password);
        tvForget.setOnClickListener(this);
        ImageView ivWeixin = (ImageView) findViewById(R.id.iv_weixin);
        ivWeixin.setOnClickListener(this);
        ImageView ivWeibo = (ImageView) findViewById(R.id.iv_weibo);
        ivWeibo.setOnClickListener(this);
        ImageView ivQq = (ImageView) findViewById(R.id.iv_qq);
        ivQq.setOnClickListener(this);

    }

    private void initQQLogin() {

        mTencent = Tencent.createInstance(ConstUtil.QQ_APP_ID, this);

        /** 判断是否登陆过 */
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, scope, loginListener);
        }/** 登陆过注销之后在登录 */
        else {
            mTencent.logout(this);
            mTencent.login(this, scope, loginListener);
        }

    }


    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            updateUserInfo();
        }
    };


    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {
                    Message msg = new Message();
                    msg.obj = "把手机时间改成获取网络时间";
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onCancel() {
                    Message msg = new Message();
                    msg.obj = "获取用户信息失败";
                    msg.what = 2;
                    mHandler.sendMessage(msg);
                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        } else {

        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        String nickname = response.getString("nickname");
                        String avatar = (String) response.get("figureurl_qq_1");
                        SharedPrefenceUtil.writeConfig(LoginActivity.this, ConstUtil.TAG_USER_NAME, nickname);
                        SharedPrefenceUtil.writeConfig(LoginActivity.this, ConstUtil.TAG_USER_AVATAR, avatar);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (msg.what == 1) {
                //mThirdLoginResult.setText(msg + "");
            } else if (msg.what == 2) {
                //mThirdLoginResult.setText(msg + "");
            }
        }

    };


    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            if (null == response) {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (jsonResponse.length() == 0) {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.v("ashin", "QQ登录成功返回结果-" + response.toString());
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject response) {
        }

        @Override
        public void onError(UiError e) {

        }

        @Override
        public void onCancel() {
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_register:
                register();
                break;
            case R.id.tv_forget_password:
                forgetPassword();
                break;
            case R.id.iv_weixin:
                qqLogin();
                break;
            case R.id.iv_weibo:
                wbLogin();
                break;
            case R.id.iv_qq:

                break;
        }
    }

    private void wbLogin() {

        mAuthInfo = new AuthInfo(this, ConstUtil.APP_KEY, ConstUtil.REDIRECT_URL, ConstUtil.SCOPE);
        WbSdk.install(this, mAuthInfo);


        mSsoHandler = new SsoHandler(this);
        mSsoHandler.authorize(new SelfWbAuthListener());


    }


    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
           /* runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // 显示 Token
                        updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);
                        Toast.makeText(WBAuthActivity.this,
                                R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                    }
                }
            });*/

            CommonUtil.showToast("授权成功", 1000);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void cancel() {
            //Toast.makeText(WBAuthActivity.this,R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
            CommonUtil.showToast("取消", 1000);
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            //Toast.makeText(WBAuthActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
            CommonUtil.showToast(errorMessage.getErrorMessage(), 1000);
        }
    }

    private void qqLogin() {

        initQQLogin();
        mTencent.login(this, scope, loginListener);

    }

    /**
     * 忘记密码
     */
    private void forgetPassword() {


    }

    /**
     * 快速注册
     */
    private void register() {
        Intent intent = new Intent(this, RegisterAccountFirstActivity.class);
        startActivity(intent);

    }

    /**
     * 登录
     */
    private void login() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mTencent != null) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }

        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
