package comele.example.admin.guoan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.network.NewsRequest;
import comele.example.admin.guoan.utlis.ConstUtil;

/**
 * Created by ashin on 2017/10/10.
 */
public class NewsDetailActivity extends AppCompatActivity {

    private static final String TAG_GET_NEWS_DETAIL = "NewsDetailActivity_get_news_detail";


    private WebView mWebView;
    private int mNewsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        EventBus.getDefault().register(this);

        mNewsId = getIntent().getIntExtra(ConstUtil.TAG_NEWS_ID, 0);
        mWebView = (WebView) findViewById(R.id.web_news);
        initData();
    }

    private void initData() {

        NewsRequest.getNewsDetail(mNewsId, TAG_GET_NEWS_DETAIL);

    }


    //新闻详情
    @Subscriber(tag = TAG_GET_NEWS_DETAIL)
    private void onGetNewsDetail(String result) {
        if (result != null) {
            mWebView.loadUrl(result);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
