package com.omg.arouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.omg.business_common.AppConfig;
import com.omg.business_common.BaseApp;

public class App extends BaseApp {
    private App mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    @Override
    public void initSDK() {

    }

    /**
     * 初始化组件APP
     */
    private void initModuleApp() {
        for (int i = 0;i < AppConfig.moduleApps.length;i++) {
            String moduleApp = AppConfig.moduleApps[i];
            try {
                Class aClass = Class.forName(moduleApp);
                Object o = aClass.newInstance();
                if (o instanceof BaseApp){
                    BaseApp baseApp = (BaseApp)o;
                    baseApp.onCreateModuleApp(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (InstantiationException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
    private void initARouter() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化
    }

    @Override
    public boolean isDebug(){
        return true;
    }
}
