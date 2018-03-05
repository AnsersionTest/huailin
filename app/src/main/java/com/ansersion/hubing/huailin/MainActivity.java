package com.ansersion.hubing.huailin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ansersion.hubing.huailin.Activity.BaseActivity;
import com.ansersion.hubing.huailin.fragment.BaseFragment;
import com.ansersion.hubing.huailin.fragment.NavigationBarFragment;
import com.ansersion.hubing.huailin.fragment.TitleBarFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BaseFragment.BackHandlerInterface {

    private BaseFragment selectedFragment;
    private static final String LOG_TAG = Thread.currentThread().getStackTrace()[1].getClassName();

    private BaseFragment titleBarFragment;
    private BaseFragment navigationBarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // Intent intentService = new Intent(this, NetCommService.class);
        // startService(intentService);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment.setMyFragManager(fm);

        titleBarFragment = new TitleBarFragment();
        navigationBarFragment = new NavigationBarFragment();

        titleBarFragment.startFragment(false);
        navigationBarFragment.startFragment(false);
        ButterKnife.bind(this);
    }

    public void hideSelectorBar() {
        navigationBarFragment.hideFragment();
    }

    public void showSelectorBar() {
        navigationBarFragment.showFragment();
    }

    public BaseFragment getTitleBar() {
        return titleBarFragment;
    }

    public BaseFragment getSelectorBar() {
        return navigationBarFragment;
    }

    public void hideTitleBar() {
        titleBarFragment.hideFragment();
    }

    public void showTitleBar() {
        titleBarFragment.showFragment();
    }

    @Override
    public void setSelectedFragment(BaseFragment backHandledFragment) {
        this.selectedFragment = backHandledFragment;
    }

    @Override
    public void onBackPressed() {
        if(selectedFragment != null) {
            selectedFragment = null;
            showSelectorBar();
            showTitleBar();
        }

        if(BaseFragment.getMyFragmentManager().getBackStackEntryCount() == 1) {
            ((TitleBarFragment)titleBarFragment).hideBackIcon();
        }

        super.onBackPressed();
    }

    public void mainPanelRestore() {
        TitleBarFragment fragment = (TitleBarFragment)titleBarFragment;
        fragment.hideBackIcon();
        showSelectorBar();
        showTitleBar();
    }

}
