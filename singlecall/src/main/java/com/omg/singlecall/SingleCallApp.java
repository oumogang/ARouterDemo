package com.omg.singlecall;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.omg.business_common.BaseApp;

public class SingleCallApp extends BaseApp {
    private SingleCallApp mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    @Override
    public void initSDK() {

    }
    @Override
    public boolean isDebug() {
        return true;
    }
}
