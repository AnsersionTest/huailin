package com.ansersion.hubing.huailin.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.ansersion.hubing.huailin.MainActivity;
import com.ansersion.hubing.huailin.application.HuailinApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by hubing on 2018/3/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    // public ContentPage contentPage;
    protected static FragmentManager fragManager = null;
    private static int fragmentCount = 0;
    // protected static Context context;
    protected BackHandlerInterface backHandlerInterface;
    protected TitleBarChangeHandlerInterface titleBarChangeHandlerInterface;
    protected int fragmentId;
    String title;
    public interface BackHandlerInterface {
        public void setSelectedFragment(BaseFragment backHandledFragment);
    }
    public interface TitleBarChangeHandlerInterface {
        public void showBackIcon();
        public void changeTitle(String title);
        // public void hideBackIcon();
    }

    public BaseFragment() {
        super();
        title = new String("");

    }

    public static void setMyFragManager(FragmentManager fm) {
        fragManager = fm;
    }

    public static FragmentManager getMyFragmentManager() {
        return fragManager;
    }

    public abstract void startFragment(boolean push_back_stack);

    public void hideFragment() {
        if(fragManager != null) {
            FragmentTransaction transaction = fragManager.beginTransaction();
            transaction.hide(this).commit();
        }
    }

    public void showFragment() {
        if(fragManager != null) {
            FragmentTransaction transaction = fragManager.beginTransaction();
            transaction.show(this).commit();
        }
    }

    public void refreshFragment() {
        FragmentManager fm = getMyFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(fragmentId, this);
        transaction.commit();
    }

    public void setFragmentId(int id) {
        fragmentId = id;
    }

    public int getFragmentId() {
        return fragmentId;
    }

    public void setTitle(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentCount++;
        if (!(getActivity() instanceof BackHandlerInterface)) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            backHandlerInterface = (BackHandlerInterface) getActivity();
            titleBarChangeHandlerInterface = (TitleBarChangeHandlerInterface)((MainActivity)getActivity()).getTitleBar();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        backHandlerInterface.setSelectedFragment(this);
        titleBarChangeHandlerInterface.showBackIcon();
        titleBarChangeHandlerInterface.changeTitle(getTitle());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        fragmentCount--;
        if(0 == fragmentCount) {
            // fragManager = null;
        }
        RefWatcher refWatcher = HuailinApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    public static void popAllBackStack() {
        for(int i = 0; i < getMyFragmentManager().getBackStackEntryCount(); i++) {
            getMyFragmentManager().popBackStack();
        }
    }

    /*
    public static void setBeecomContext(Context ct) {
        context = ct;
    }

    public static Context getBeecomContext() {
        return context;
    }
    */

}
