package com.ansersion.hubing.huailin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ansersion.hubing.huailin.R;
import com.ansersion.hubing.huailin.customview.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hubing on 2018/4/8.
 */

public class MeFragment extends BaseFragment {
    private static final String LOG_TAG = "MeFragment";

    private SimpleAdapter adapterGridView;
    private List<Map<String, Object>> dataListGridView;

    @Bind(R.id.id_grid_view_me_layout)
    MyGridView meMyGridView;

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle(getResources().getString(R.string.layout_name_me));
        setFragmentId(R.layout.fragment_me);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentId(), container, false);
        ButterKnife.bind(this, view);

        int icno[] = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
        };
        //图标下的文字
        String name[]={"项目1","项目2","项目3","项目4","项目5","项目6","项目7","项目8"};
        dataListGridView = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text",name[i]);
            dataListGridView.add(map);
        }

        String[] from = {"img", "text"};
        int[] to = {R.id.id_grid_view_img, R.id.id_grid_view_txt};
        adapterGridView = new SimpleAdapter(getActivity(), dataListGridView, R.layout.classify_gridview_item, from, to);

        meMyGridView.setAdapter(adapterGridView);

        return view;
    }

    @Override
    public void startFragment(boolean push_back_stack) {
        FragmentManager fm = getMyFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.id_fragment_main, this);
        if(push_back_stack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
