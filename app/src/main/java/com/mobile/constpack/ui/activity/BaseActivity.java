package com.mobile.constpack.ui.activity;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    @AfterViews
    public abstract void initViews();
}
