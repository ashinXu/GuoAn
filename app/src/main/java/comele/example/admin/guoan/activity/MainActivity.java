package comele.example.admin.guoan.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.adapter.HomePageFragmentAdapter;
import comele.example.admin.guoan.utlis.CommonUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private HomePageFragmentAdapter mAdapter;
    private TabLayout mTabLayout;
    private List<String> titles = new ArrayList<>();
    private TextView tvMain;
    private TextView tvMy;
    private TextView tvMessage;
    private TextView tvSetting;
    private TextView tvAbout;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setItemIconTintList(null);


        ImageView ivSliding = (ImageView) findViewById(R.id.iv_sliding);
        ivSliding.setOnClickListener(this);


        View headerView = mNavigationView.getHeaderView(0);
        tvMain = (TextView) headerView.findViewById(R.id.tv_to_main);
        tvMain.setOnClickListener(this);

        tvMy = (TextView) headerView.findViewById(R.id.tv_my);
        tvMy.setOnClickListener(this);

        tvMessage = (TextView) headerView.findViewById(R.id.tv_message);
        tvMessage.setOnClickListener(this);

        tvSetting = (TextView) headerView.findViewById(R.id.tv_setting);
        tvSetting.setOnClickListener(this);

        tvAbout = (TextView) headerView.findViewById(R.id.tv_about);
        tvAbout.setOnClickListener(this);

        titles.add("头条");
        titles.add("聚合");
        titles.add("生活");
        titles.add("活动");
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mAdapter = new HomePageFragmentAdapter(getSupportFragmentManager(), titles);

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(0);//判断是从哪跳进来的
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_main:
                CommonUtil.showToast("tv_to_main", 1000);
                break;
            case R.id.tv_my:
                CommonUtil.showToast("tv_my", 1000);
                break;
            case R.id.tv_message:
                CommonUtil.showToast("tv_message", 1000);
                break;
            case R.id.tv_setting:
                CommonUtil.showToast("tv_setting", 1000);
                break;
            case R.id.tv_about:
                CommonUtil.showToast("tv_about", 1000);
                break;
            case R.id.iv_sliding:
                //打开侧边栏
                mDrawerLayout.openDrawer(mNavigationView);
                break;
        }
    }
}
