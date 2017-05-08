package com.mobile.constpack.ui.activity;

import android.content.Intent;
import android.os.Handler;

import com.mobile.constpack.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class SplashController extends BaseActivity {

    @AfterViews
    public void initViews() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity_.intent(SplashController.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
                finish();
            }
        },2000);
    }
}
