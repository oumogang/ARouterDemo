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
    }

    public void routeToGroupCallByUri(View view){
        Uri uri = Uri.parse("/groupcall/activity/activity");
        ARouter.getInstance().build(uri).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","GroupCall界面,MainActivity通过ARouter URI 启动")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }

    public void routeToGroupCallByPath(View view){
        String path = "/groupcall/activity/activity";
        ARouter.getInstance().build(path).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","GroupCall界面,MainActivity通过ARouter path 启动")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }

    /**
     *  转场动画(API16+)
     * @return
     */
    private ActivityOptionsCompat getDefaultCompat() {
        View v = getWindow().getDecorView();
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
        return  compat;
    }

    public void routeToSingleCallByUri(View view){
        Uri uri = Uri.parse("/singlecall/activity");
        ARouter.getInstance().build(uri).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","SingleCall界面,MainActivity通过ARouter URI 启动")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }
    public void routeToSingleCallByPath(View view){
        String path = "/singlecall/activity";
        ARouter.getInstance().build(path).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","SingleCall界面,MainActivity通过ARouter path 启动")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }
}
