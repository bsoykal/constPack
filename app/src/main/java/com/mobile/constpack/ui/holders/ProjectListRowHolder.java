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

@EViewGroup(R.layout.row_project_list)
public class ProjectListRowHolder extends RelativeLayout {

    @ViewById(R.id.apptxt_row)
    AppCompatTextView apptxt_row;

    public ProjectListRowHolder(Context context) {
        super(context);
    }

    public void bind(String text) {
        apptxt_row.setText(text);
    }
}