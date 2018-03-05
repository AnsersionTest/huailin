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

    @Bind(R.id.id_main_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    private BaseFragment classifyFragment;
    private BaseFragment notifyFragment;
    private BaseFragment meFragment;

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragmentId(R.layout.fragment_navigation);

        /*
        bcFragment = new BeecomFragment();
        mnFragment = new ManageFragment();
        shFragment = new ShareFragment();
        meFragment = new MeFragment();
        */

        // bcFragment.startFragment(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentId(), container, false);
        ButterKnife.bind(this, view);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                // mTextMessage.setText(R.string.title_class);
                                return true;
                            case R.id.navigation_dashboard:
                                // mTextMessage.setText(R.string.title_message);
                                return true;
                            case R.id.navigation_notifications:
                                // mTextMessage.setText(R.string.title_me);
                                return true;
                        }
                        return false;
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
