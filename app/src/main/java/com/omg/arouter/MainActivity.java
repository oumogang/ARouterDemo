package com.omg.arouter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDelay(1000);

    }

    private void navigationDelay(int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                navigationOnUi();
            }
        }).start();
    }

    private void navigationOnUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                navigationNow();
            }
        });
    }

    private void navigationNow() {
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//        ARouter.getInstance().build("/call/activity").navigation();
//        ARouter.getInstance().build("/call/activity")
//                .withString("name","中国移动")
//                .withString("number","10086")
//                .withString("message","这是MainActivity通过ARouter启动的界面")
//                .withLong("date",System.currentTimeMillis())
//                .navigation();

        // 转场动画(API16+)
        View v = getWindow().getDecorView();
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);

        ARouter.getInstance().build("/singlecall/activity")
                .withString("name","中国移动")
                .withString("number","10086")
                .withString("message","这是MainActivity通过ARouter启动的界面 /singlecall/activity")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(compat)//跳转动画
                .navigation();

        routeByUri(compat);
    }

    private void routeByUri(ActivityOptionsCompat compat) {
        Uri uri = Uri.parse("/singlecall/activity");
        ARouter.getInstance().build(uri).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","这是MainActivity通过ARouter URI 启动的界面")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(compat)//跳转动画
                .navigation();
    }
}
