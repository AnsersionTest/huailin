package com.ansersion.hubing.huailin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ansersion.hubing.huailin.MainActivity;
import com.ansersion.hubing.huailin.R;
import com.ansersion.hubing.huailin.adapter.ClassifyListViewAdapter;
import com.ansersion.hubing.huailin.adapter.ClassifyListViewItem;
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

public class ClassifyFragment extends BaseFragment {
    private static final String LOG_TAG = "ClassifyFragment";
    private List<ClassifyListViewItem> fruitList = new ArrayList<ClassifyListViewItem>();

    @Bind(R.id.id_list_view_classify)
    ListView classifyListView;

    MyGridView listViewHeaderGridview;
    private SimpleAdapter adapterGridView;
    private List<Map<String, Object>> dataListGridView;

    private void initFruits() {
        ClassifyListViewItem apple = new ClassifyListViewItem("Apple", 1);
        fruitList.add(apple);
        ClassifyListViewItem banana = new ClassifyListViewItem("Banana", 2);
        fruitList.add(banana);
        ClassifyListViewItem orange = new ClassifyListViewItem("Orange", 3);
        fruitList.add(orange);
        ClassifyListViewItem watermelon = new ClassifyListViewItem("Watermelon", 4);
        fruitList.add(watermelon);
        ClassifyListViewItem pear = new ClassifyListViewItem("Pear", 5);
        fruitList.add(pear);
        ClassifyListViewItem grape = new ClassifyListViewItem("Grape", 6);
        fruitList.add(grape);
        ClassifyListViewItem pineapple = new ClassifyListViewItem("Pineapple", 7);
        fruitList.add(pineapple);
        ClassifyListViewItem strawberry = new ClassifyListViewItem("Strawberry", 8);
        fruitList.add(strawberry);
        ClassifyListViewItem cherry = new ClassifyListViewItem("Cherry", 9);
        fruitList.add(cherry);
        ClassifyListViewItem mango = new ClassifyListViewItem("Mango", 10);
        fruitList.add(mango);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle(getResources().getString(R.string.layout_name_me));
        setFragmentId(R.layout.fragment_classify);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentId(), container, false);
        ButterKnife.bind(this, view);

        View headerParent = LayoutInflater.from(getActivity()).inflate(R.layout.classify_listview_header_gridview, null);
        listViewHeaderGridview = headerParent.findViewById(R.id.id_grid_view_list_view_header);

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

        listViewHeaderGridview.setAdapter(adapterGridView);

        classifyListView.addHeaderView(headerParent);

        initFruits();
        ClassifyListViewAdapter adapter = new ClassifyListViewAdapter(getActivity(), R.layout.classify_listview_item, fruitList);
        classifyListView.setAdapter(adapter);

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
