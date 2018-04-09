package com.ansersion.hubing.huailin.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ansersion.hubing.huailin.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hubing on 2018/3/5.
 */

public class NavigationBarFragment extends BaseFragment {

    private static final String LOG_TAG = Thread.currentThread().getStackTrace()[1].getClassName();

    @Bind(R.id.id_radio_button_classify)
    RadioButton rbtClassify;
    @Bind(R.id.id_radio_button_message)
    RadioButton rbtMessage;
    @Bind(R.id.id_radio_button_me)
    RadioButton rbtMe;
    @Bind(R.id.id_radio_group)
    RadioGroup radioGroup;

    private BaseFragment classifyFragment;
    private BaseFragment messageFragment;
    private BaseFragment meFragment;

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragmentId(R.layout.fragment_navigation);


        classifyFragment = new ClassifyFragment();
        messageFragment = new MessageFragment();
        meFragment = new MeFragment();

        classifyFragment.startFragment(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentId(), container, false);
        ButterKnife.bind(this, view);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_radio_button_classify:
                        classifyFragment.startFragment(false);
                        break;
                    case R.id.id_radio_button_message:
                        messageFragment.startFragment(false);
                        break;
                    case R.id.id_radio_button_me:
                        meFragment.startFragment(false);
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void startFragment(boolean push_back_stack) {
        FragmentTransaction transaction = getMyFragmentManager().beginTransaction();
        transaction.replace(R.id.id_fragment_navigation_bar, this);
        if(push_back_stack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
