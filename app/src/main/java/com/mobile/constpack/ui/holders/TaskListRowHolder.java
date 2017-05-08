package com.mobile.constpack.ui.holders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mobile.constpack.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 03/02/2017.
 */

@EViewGroup(R.layout.row_task_list)
public class TaskListRowHolder extends RelativeLayout {

    @ViewById(R.id.job_title_name)
    AppCompatTextView job_title_name;

    @ViewById(R.id.prime_title_name)
    AppCompatTextView prime_title_name;

    @ViewById(R.id.count_title_name)
    AppCompatTextView count_title_name;

    @ViewById(R.id.ll_background)
    LinearLayout ll_background;

    private Context context;

    public TaskListRowHolder(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(String job,String prime, String count,boolean done) {
        job_title_name.setText(job);
        prime_title_name.setText(prime);
        count_title_name.setText(count);
        ll_background.setBackgroundColor(done? ContextCompat.getColor(context,R.color.job_done):ContextCompat.getColor(context,R.color.white));
    }
}