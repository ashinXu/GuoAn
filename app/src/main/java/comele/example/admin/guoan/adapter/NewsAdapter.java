package comele.example.admin.guoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comele.example.admin.guoan.R;
import comele.example.admin.guoan.bean.ResponseBean;
import comele.example.admin.guoan.utlis.CommonUtil;

/**
 * Created by admin on 2017/10/9.
 */
public class NewsAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResponseBean.NewsEntity> mDatas;


    public NewsAdapter(Context context, List<ResponseBean.NewsEntity> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false);
            holder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_summary);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.ivNews = (ImageView) convertView.findViewById(R.id.iv_news);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResponseBean.NewsEntity bean = mDatas.get(position);

        holder.tvSource.setText(bean.extraData);
        holder.tvTitle.setText(bean.title);
        holder.tvTime.setText(bean.updateTime);

        CommonUtil.loadImage(holder.ivNews,bean.photo,"");

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class ViewHolder {
        TextView tvSource;
        TextView tvTitle;
        TextView tvTime;
        ImageView ivNews;
    }


}
