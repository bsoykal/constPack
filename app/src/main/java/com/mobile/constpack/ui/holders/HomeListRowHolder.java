package com.mobile.constpack.ui.holders;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import com.mobile.constpack.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 03/02/2017.
 */

@EViewGroup(R.layout.row_home_list)
public class HomeListRowHolder extends RelativeLayout {

    @ViewById(R.id.appimg_row)
    AppCompatImageView appimg_row;

    @ViewById(R.id.appimg_row2)
    AppCompatImageView appimg_row2;

    @ViewById(R.id.apptxt_row)
    AppCompatTextView apptxt_row;

    public HomeListRowHolder(Context context) {
        super(context);
    }

    public void bind(int drawable,String text) {
        appimg_row.setImageResource(drawable);
        appimg_row2.setImageResource(drawable);
        apptxt_row.setText(text);
    }
}