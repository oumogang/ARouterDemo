package com.omg.arouter.interceptor;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority=8,name = "call interceptor")
public class CallInterceptor implements IInterceptor {
    private static final String TAG = CallInterceptor.class.getSimpleName();
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i(TAG, "process: postcard path"+postcard.getPath());
        if (postcard.getPath().contains("groupcall")){
            Bundle extras = postcard.getExtras();
            if (null == extras || TextUtils.isEmpty(extras.getString("number"))){
                callback.onInterrupt(new RuntimeException("empty number exception"));
            }else {
                callback.onContinue(postcard);
            }
        }else if (postcard.getPath().contains("singlecall")){
            postcard.withString("name","new name set by CallInterceptor");
            callback.onContinue(postcard);
        }else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
