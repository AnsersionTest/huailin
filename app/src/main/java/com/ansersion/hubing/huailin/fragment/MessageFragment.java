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

import com.ansersion.hubing.huailin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hubing on 2018/4/8.
 */

public class MessageFragment extends BaseFragment {
    private static final String LOG_TAG = "MessageFragment";

    private String[] mListTitle = {"contact1", "contact2"};
    private String[] mListText = {"t1", "t2"};

    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();

    @Bind(R.id.id_list_view_message)
    ListView messageListView;

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle(getResources().getString(R.string.layout_name_me));
        setFragmentId(R.layout.fragment_message);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentId(), container, false);
        ButterKnife.bind(this, view);

        int lengh = mListTitle.length;
        if(mData.size() == 0) {
            for (int i = 0; i < lengh; i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                if (i == 0) {
                    item.put("image", R.mipmap.common);
                } else {
                    item.put("image", R.mipmap.big);
                }
                item.put("title", mListTitle[i]);
                item.put("text", mListText[i]);
                mData.add(item);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,R.layout.message_listview_item,
                new String[]{"image","title","text"},new int[]{R.id.image,R.id.title,R.id.text});

        messageListView.setAdapter(adapter);

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
