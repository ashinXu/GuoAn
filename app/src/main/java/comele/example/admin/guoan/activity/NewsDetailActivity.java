package comele.example.admin.guoan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.network.NewsRequest;
import comele.example.admin.guoan.utlis.CommonUtil;
import comele.example.admin.guoan.utlis.ConstUtil;

/**
 * Created by ashin on 2017/10/10.
 */
public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_GET_NEWS_DETAIL = "NewsDetailActivity_get_news_detail";
    private static final String TAG_PRAISE_NEWS = "NewsDetailActivity_praise_news";


    private WebView mWebView;
    private int mNewsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        EventBus.getDefault().register(this);

        mNewsId = getIntent().getIntExtra(ConstUtil.TAG_NEWS_ID, 0);

        initView();
        initData();
    }

    private void initView() {

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("新闻详情");

        mWebView = (WebView) findViewById(R.id.web_news);
        ImageView ivPraise = (ImageView) findViewById(R.id.iv_praise);
        ivPraise.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_praise:
                //新闻点赞
                NewsRequest.praiseNews(mNewsId, TAG_PRAISE_NEWS);
                break;
        }
    }


    /**
     * 新闻点赞
     *
     * @param result
     */
    @Subscriber(tag = TAG_PRAISE_NEWS)
    private void onPraiseNews(Boolean result) {
        if (result != null) {

            CommonUtil.showToast("点赞成功", 1000);

        }
    }
}
