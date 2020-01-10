package com.omg.arouter;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.omg.business_common.contact.Contact;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * uri 启动，传基本数据类型数据
     * @param view
     */
    public void routeToGroupCallByUri(View view){
        Contact contact = new Contact();
        contact.setName("中国移动");
        contact.setNumber("10086");
        Uri uri = Uri.parse("/groupcall/activity/activity");
        ARouter.getInstance().build(uri).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","GroupCall界面,MainActivity通过ARouter URI 启动")
                .withLong("date",System.currentTimeMillis())
                .withObject("contact",contact)
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }

    /**
     * path 启动，传基本数据类型数据
     * @param view
     */
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
     * 传Object数据
     * @param view
     */
    public void routeToGroupCallByPathWithObject(View view){
        Contact contact = new Contact("中国移动","10086");
        String path = "/groupcall/activity/activity";
        ARouter.getInstance().build(path).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","GroupCall界面,MainActivity通过ARouter path 启动")
                .withLong("date",System.currentTimeMillis())
                .withObject("contact",contact)
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

    /**
     * uri启动
     * @param view
     */
    public void routeToSingleCallByUri(View view){
        Uri uri = Uri.parse("/singlecall/activity");
        ARouter.getInstance().build(uri).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","SingleCall界面,MainActivity通过ARouter URI 启动")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }

    /**
     * path 启动
     * @param view
     */
    public void routeToSingleCallByPath(View view){
        Contact contact = new Contact("中国移动","10086");
        String path = "/singlecall/activity";
        ARouter.getInstance().build(path).withString("name",contact.getName())
                .withString("number",contact.getNumber())
                .withString("message","SingleCall界面,MainActivity通过ARouter path 启动")
                .withLong("date",System.currentTimeMillis())
                .withObject("contact",contact)
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation();
    }

    /**
     * 回调:lost found interrupt and arrival
     * @param view
     */
    public void routeToSingleCallByPathWithCallBack(View view){
        String path = "/singlecall/activity";
        NavCallback navCallback = new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                Log.i(TAG, "onArrival: postcard path = "+postcard.getPath());
                Toast.makeText(MainActivity.this,"onArrival: postcard path = "+postcard.getPath(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFound(Postcard postcard) {
                super.onFound(postcard);
                Log.i(TAG, "onFound: postcard path = "+postcard.getPath());
                Toast.makeText(MainActivity.this,"onFound: postcard path = "+postcard.getPath(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                super.onInterrupt(postcard);
                Log.i(TAG, "onInterrupt: postcard path = "+postcard.getPath());
                Toast.makeText(MainActivity.this,"onInterrupt: postcard path = "+postcard.getPath(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLost(Postcard postcard) {
                super.onLost(postcard);
                Log.i(TAG, "onLost: postcard path = "+postcard.getPath());
                Toast.makeText(MainActivity.this,"onLost: postcard path = "+postcard.getPath(),Toast.LENGTH_LONG).show();
            }
        };
        ARouter.getInstance().build(path).withString("name","中国移动")
                .withString("number","10086")
                .withString("message","SingleCall界面,MainActivity通过ARouter path 启动")
                .withLong("date",System.currentTimeMillis())
                .withOptionsCompat(getDefaultCompat())//跳转动画
                .navigation(this,navCallback );
    }
}
