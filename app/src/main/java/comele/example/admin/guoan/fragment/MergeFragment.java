package comele.example.admin.guoan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comele.example.admin.guoan.R;

/**
 * Created by admin on 2017/9/25.
 */
public class MergeFragment extends Fragment {


    int mNum; //页号

    public static MergeFragment newInstance(int num) {
        MergeFragment fragment = new MergeFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里我只是简单的用num区别标签，其实具体应用中可以使用真实的fragment对象来作为叶片
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * 为Fragment加载布局时调用
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_merge, null);
        return view;
    }
}
