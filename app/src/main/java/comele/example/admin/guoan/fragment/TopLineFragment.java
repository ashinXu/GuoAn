package comele.example.admin.guoan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.youth.banner.Banner;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.activity.AccountManageActivity;
import comele.example.admin.guoan.activity.CurrentTopActivity;
import comele.example.admin.guoan.activity.NewsDetailActivity;
import comele.example.admin.guoan.adapter.NewsAdapter;
import comele.example.admin.guoan.bean.ResponseBean;
import comele.example.admin.guoan.network.NewsRequest;
import comele.example.admin.guoan.utlis.ConstUtil;
import comele.example.admin.guoan.utlis.GlideImageLoader;

/**
 * Created by admin on 2017/9/25.
 */
public class TopLineFragment extends Fragment implements View.OnClickListener {

    private static final String TAG_GET_DATA = "TopLineFragment_get_data";
    private static final String TAG_GET_NEWS_LIST = "TopLineFragment_get_news_list`";

    int mNum; //页号
    private List<String> banners = new ArrayList<>();
    private Banner mBanner;
    private ListView mLvNews;
    private NewsAdapter mAdapter;
    private List<ResponseBean.NewsEntity> mNews = new ArrayList<>();

    public static TopLineFragment newInstance(int num) {
        TopLineFragment fragment = new TopLineFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        //这里我只是简单的用num区别标签，其实具体应用中可以使用真实的fragment对象来作为叶片
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * 为Fragment加载布局时调用
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_line, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

        NewsRequest.getCover(TAG_GET_DATA);

        NewsRequest.getNewsList(0, 10, TAG_GET_NEWS_LIST);
    }

    private void initView(View view) {

        TextView tvTop = (TextView) view.findViewById(R.id.tv_top_today);
        tvTop.setOnClickListener(this);
        TextView tvHot = (TextView) view.findViewById(R.id.tv_hot);
        tvHot.setOnClickListener(this);
        TextView tvAccount = (TextView) view.findViewById(R.id.tv_account);
        tvAccount.setOnClickListener(this);


        mBanner = (Banner) view.findViewById(R.id.banner);

        mBanner.setImageLoader(new GlideImageLoader());

        mLvNews = (ListView) view.findViewById(R.id.lv_news);


        mLvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResponseBean.NewsEntity entity = mNews.get(position);
                int newsId = entity.id;
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra(ConstUtil.TAG_NEWS_ID, newsId);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_top_today:
                //今日头条
                Intent topIntent = new Intent(getActivity(), CurrentTopActivity.class);
                startActivity(topIntent);
                break;
            case R.id.tv_hot:
                //热门话题

                break;
            case R.id.tv_account:
                //我的信息
                Intent intent = new Intent(getActivity(), AccountManageActivity.class);
                startActivity(intent);
                break;
        }
    }


    //获取首页新闻数据
    @Subscriber(tag = TAG_GET_NEWS_LIST)
    private void onGetNewsList(List<ResponseBean.NewsEntity> result) {
        if (result != null) {
            for (ResponseBean.NewsEntity bean : result) {
                Log.v("ashin", bean.toString());
            }

            mNews = result;
            mAdapter = new NewsAdapter(getActivity(), result);
            mLvNews.setAdapter(mAdapter);

        }
    }


    //获取banner数据
    @Subscriber(tag = TAG_GET_DATA)
    private void onGetBannerList(List<ResponseBean.Cover> result) {
        banners.clear();
        if (result != null) {
            Log.v("ashin", "获取成功");
            for (ResponseBean.Cover bean : result) {
                if (!TextUtils.isEmpty(bean.picUrl)) {
                    banners.add(bean.picUrl);
                }
            }

            setData();
        }
    }

    private void setData() {

        Log.v("ashin", "" + banners.size());

        //设置图片集合
        mBanner.setImages(banners);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}