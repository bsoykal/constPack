package com.mobile.constpack.ui.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mobile.constpack.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 16/02/2017.
 */

@EActivity(R.layout.activity_news)
public class NewsActivity extends BaseActivity {

    @ViewById(R.id.news_webview)
    WebView news_web_view;

    @AfterViews
    public void initViews(){

        news_web_view.getSettings().setJavaScriptEnabled(true);

        showLoadingDialog();
        news_web_view.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissLoadingDialog();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                dismissLoadingDialog();
                Toast.makeText(NewsActivity.this,description,Toast.LENGTH_LONG).show();
            }
        });

        news_web_view.loadUrl("http://www.constpack.com/category/sektor-haberleri/");


    }




}
