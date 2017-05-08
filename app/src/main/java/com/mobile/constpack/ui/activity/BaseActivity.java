package com.mobile.constpack.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;

import com.mobile.constpack.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 04/02/2017.
 */

@EActivity
public class BaseActivity extends AppCompatActivity {

    private AppCompatDialog dialogLoading;
    private int loadingProgressCounter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        final LoadingDialogViewHolder loadingDialogViewHolder = BaseActivity_.LoadingDialogViewHolder_.build(this);
        final AnimationDrawable animationDrawable = (AnimationDrawable) loadingDialogViewHolder.appimgLoading.getDrawable();

        dialogLoading = new AppCompatDialog(this, R.style.loading_dialog);
        dialogLoading.setContentView(loadingDialogViewHolder);
        dialogLoading.setCancelable(false);
        dialogLoading.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ViewCompat.postOnAnimation(loadingDialogViewHolder.appimgLoading, new Runnable() {
                    @Override
                    public void run() {
                        animationDrawable.start();
                    }
                });
            }
        });
        dialogLoading.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ViewCompat.postOnAnimation(loadingDialogViewHolder.appimgLoading, new Runnable() {
                    @Override
                    public void run() {
                        animationDrawable.stop();
                    }
                });
            }
        });

    }

    public void showLoadingDialog() {
        if (!dialogLoading.isShowing() && loadingProgressCounter == 0)
            dialogLoading.show();
        loadingProgressCounter++;
    }

    public void dismissLoadingDialog() {
        loadingProgressCounter--;
        if (dialogLoading.isShowing() && loadingProgressCounter == 0)
            dialogLoading.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
    }

    @EViewGroup(R.layout.dialog_loading)
    static class LoadingDialogViewHolder extends FrameLayout {
        @ViewById(R.id.appimg_loading)
        AppCompatImageView appimgLoading;

        public LoadingDialogViewHolder(Context context) {
            super(context);
        }
    }


}
