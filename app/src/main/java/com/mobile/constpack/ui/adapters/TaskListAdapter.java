package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.network.domain.Task;
import com.mobile.constpack.ui.holders.TaskListRowHolder;
import com.mobile.constpack.ui.holders.TaskListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class TaskListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @Setter
    Task[] tasks;

    @Override
    public int getCount() {
        return tasks.length;
    }

    @Override
    public Task getItem(int position) {
        return tasks[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       TaskListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = TaskListRowHolder_.build(context);
        } else {
            viewHolder = (TaskListRowHolder) convertView;
        }

        viewHolder.bind(tasks[position].getIsAdi(),tasks[position].getSorumlu(),""+tasks[position].getMiktar(),tasks[position].isTamamlandi());

        return viewHolder;
    }

    @Override
    public int getViewTypeCount() {
        // menu type count
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return !tasks[position].isTamamlandi()?0:1;
    }


}
