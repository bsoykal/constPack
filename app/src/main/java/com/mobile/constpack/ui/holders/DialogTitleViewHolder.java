package com.mobile.constpack.ui.holders;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import com.mobile.constpack.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 03/02/2017.
 */

@EViewGroup(R.layout.title_dialog)
public class DialogTitleViewHolder extends RelativeLayout {

    @ViewById(R.id.apptxt_dialog_title)
    AppCompatTextView apptxtDialogTitle;


    public DialogTitleViewHolder(Context context,String title) {
        super(context);
    }

    public void bind(String text) {
        apptxtDialogTitle.setText(text);
    }
}