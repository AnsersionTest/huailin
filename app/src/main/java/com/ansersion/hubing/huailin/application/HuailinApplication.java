package com.ansersion.hubing.huailin.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.litepal.LitePal;

/**
 * Created by hubing on 2018/3/5.
 */

public class HuailinApplication extends Application {
    public static final int ACCESS_COARSE_LOCATION_REQ_CODE = 0xFF01;

    private static Context context;


    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        HuailinApplication application = (HuailinApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        context = getApplicationContext();

        refWatcher = LeakCanary.install(this);



    }

    public static Context getContext() {
        return context;
    }

    // public BeecomApplication getBeecomApplication() { return this; }
}
