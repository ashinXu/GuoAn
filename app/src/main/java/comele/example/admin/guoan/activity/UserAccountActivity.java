package comele.example.admin.guoan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import comele.example.admin.guoan.R;

/**
 * Created by admin on 2017/9/25.
 */
public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView mIvAvatar;
    private TextView mTvName;
    private RelativeLayout mRlRank;
    private RelativeLayout mRlCollect;
    private RelativeLayout mRlScore;
    private RelativeLayout mRlFeed;
    private RelativeLayout mRlAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        initView();

    }

    private void initView() {

        mIvAvatar = (ImageView) findViewById(R.id.iv_avatar);

        mTvName = (TextView) findViewById(R.id.tv_name);

        mRlRank = (RelativeLayout) findViewById(R.id.rl_rank);
        mRlRank.setOnClickListener(this);
        mRlCollect = (RelativeLayout) findViewById(R.id.rl_collect);
        mRlCollect.setOnClickListener(this);
        mRlScore = (RelativeLayout) findViewById(R.id.rl_score);
        mRlScore.setOnClickListener(this);
        mRlFeed = (RelativeLayout) findViewById(R.id.rl_feed);
        mRlFeed.setOnClickListener(this);
        mRlAccount = (RelativeLayout) findViewById(R.id.rl_account);
        mRlAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_rank:

                break;
            case R.id.rl_collect:

                break;
            case R.id.rl_score:

                break;
            case R.id.rl_feed:

                break;
            case R.id.rl_account:
                break;

        }
    }
}
