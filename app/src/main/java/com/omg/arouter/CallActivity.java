package com.omg.arouter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.omg.arouter.databinding.ActivityCallBinding;
import com.omg.business_common.contact.Contact;

@Route(path = "/call/activity")
public class CallActivity extends Activity {
    ActivityCallBinding mViewDataBinding;
    Contact mContact;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_call);
        mContact = getContact();
        String msg = getMessage();
        showTitle(msg);
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
