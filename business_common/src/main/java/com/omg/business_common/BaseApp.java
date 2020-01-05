package com.omg.business_common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * init common sdks
 * for app and modules
 */
public abstract class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        onCreateModuleApp(this);
    }
    public abstract void initSDK();
    public abstract boolean isDebug();
    public void onCreateModuleApp(Application application){
        ARouter.openLog();    // 打印日志
        ARouter.openDebug();  // 开启调试
        initARouter(application);
    };
    private void initARouter(Application application) {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
    }
}
