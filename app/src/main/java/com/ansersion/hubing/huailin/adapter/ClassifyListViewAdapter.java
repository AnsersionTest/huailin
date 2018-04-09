package com.ansersion.hubing.huailin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansersion.hubing.huailin.R;

import java.util.List;

/**
 * Created by hubing on 2018/4/8.
 */

public class ClassifyListViewAdapter extends ArrayAdapter {
    private final int itemLayoutId;

    public ClassifyListViewAdapter(Context context,  int itemLayoutId, List<ClassifyListViewItem> objects) {
        super(context, itemLayoutId, objects);
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassifyListViewItem classifyListViewItem = (ClassifyListViewItem) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(itemLayoutId, null);//实例化一个对象
        TextView title = (TextView) view.findViewById(R.id.id_classify_item_title_text_view);//获取该布局内的文本视图
        TextView price = (TextView)view.findViewById(R.id.id_classify_item_price_text_view);
        title.setText(classifyListViewItem.getTitle());//为图片视图设置图片资源
        title.setText(new Integer(classifyListViewItem.getPrice()).toString());//为文本视图设置文本内容
        return view;
    }
}
