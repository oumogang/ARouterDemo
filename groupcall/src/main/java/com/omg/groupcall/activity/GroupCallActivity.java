package com.omg.groupcall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.omg.business_common.contact.Contact;
import com.omg.groupcall.R;
import com.omg.groupcall.databinding.ActivityGroupCallBinding;

@Route(path = "/groupcall/activity/activity")
public class GroupCallActivity extends Activity {
    ActivityGroupCallBinding mViewDataBinding;
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
    @Autowired
    public Contact contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_group_call);
        // init field
        ARouter.getInstance().inject(this);
        updateViews();
    }

    private void updateViews() {
        mViewDataBinding.title.setText(TextUtils.isEmpty(message)?"no message":message);
//        mViewDataBinding.name.setText(TextUtils.isEmpty(name)?"no name":name);
//        mViewDataBinding.number.setText(TextUtils.isEmpty(number)?"no number":number);
        Contact c = new Contact();
        c.setName(TextUtils.isEmpty(name)?"no name":name);
        c.setNumber(TextUtils.isEmpty(number)?"no number":number);
        mViewDataBinding.setContact(new Contact(name,number));
        mViewDataBinding.contact.setText((null==contact)?"no contact object":(contact.toString()+"["+contact.name+","+contact.number+"]"));
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
}
