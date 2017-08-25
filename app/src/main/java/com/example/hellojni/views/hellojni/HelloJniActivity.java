package com.example.hellojni.views.hellojni;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellojni.R;

public class HelloJniActivity extends AppCompatActivity implements HelloJniMvpView {

    private HelloPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);

        mPresenter = new HelloPresenter();
        mPresenter.attachView(this);

        executeNativeMethod();
    }

    private void executeNativeMethod() {
        TextView tv = (TextView) findViewById(R.id.hello_textview);
        tv.setText("Welcome to JNI | SPINE");

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mPresenter.helloFromJni();
                //mPresenter.performXyz();
                mPresenter.modifyObject();
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
