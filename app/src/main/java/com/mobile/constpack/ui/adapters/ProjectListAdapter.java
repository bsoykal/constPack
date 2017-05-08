package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.network.domain.Project;
import com.mobile.constpack.ui.holders.ProjectListRowHolder;
import com.mobile.constpack.ui.holders.ProjectListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class ProjectListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @Setter
    Project[] projects;

    @Override
    public int getCount() {
        return projects.length;
    }

    @Override
    public Project getItem(int position) {
        return projects[position];
    }

    @Override
    public long getItemId(int position) {
        return projects[position].getProjeId();
    }

    public int getProjectId(int position){
        return projects[position].getProjeId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = ProjectListRowHolder_.build(context);
        } else {
            viewHolder = (ProjectListRowHolder) convertView;
        }

        viewHolder.bind(projects[position].getProjeAdi());

        return viewHolder;
    }


}
