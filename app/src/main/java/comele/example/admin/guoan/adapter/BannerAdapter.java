package comele.example.admin.guoan.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by admin on 2017/10/4.
 */
public class BannerAdapter extends PagerAdapter {

    private List<ImageView> mDatas;

    public BannerAdapter(List<ImageView> images) {
        this.mDatas = images;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mDatas.get(position));
        return mDatas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDatas.get(position));
    }
}
