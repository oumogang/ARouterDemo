package com.omg.singlecall;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.routes.ARouter$$Group$$singlecall;
import com.omg.singlecall.databinding.ActivitySingleCallBinding;

@Route(path = "/singlecall/activity")
public class SingleCallActivity extends Activity {
    ActivitySingleCallBinding mViewDataBinding;
    Contact mContact;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 注入条件是字段名与key一致。
    @Autowired
    public String name;
    @Autowired
    public String message;
    @Autowired
    public String number;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_call);
        // init field
        ARouter.getInstance().inject(this);
        // use field
        String msg = /*getMessage()*/message;
        showTitle(msg);
        mContact = getContact();
        // use field
        mContact.setName(name);
        mContact.setNumber(number);
        showContactData(mContact);
    }

    private void showTitle(String msg) {
        if (TextUtils.isEmpty(msg))return;
        mViewDataBinding.title.setText(msg);
    }

    private String getMessage() {
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return null;
        }
        return extras.getString("message");
    }

    private Contact getContact() {
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            throw new RuntimeException("extras must not be null");
        }
        String name = extras.getString(Contact.KEY_NAME);
        String number = extras.getString(Contact.KEY_NUMBER);
        Contact contact = new Contact(name,number);
        return contact;
    }

    private void showContactData(Contact contact) {
        mViewDataBinding.setContact(contact);
    }
}
