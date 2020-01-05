package com.omg.singlecall.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

public class SchemeFilterActivity extends Activity {
    private static final String TAG = SchemeFilterActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        Log.i(TAG, "onCreate: navigation  uri = "+uri.getPath());
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
