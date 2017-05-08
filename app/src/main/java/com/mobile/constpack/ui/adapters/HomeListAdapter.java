package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.R;
import com.mobile.constpack.ui.holders.HomeListRowHolder;
import com.mobile.constpack.ui.holders.HomeListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class HomeListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    String[] titles = {"Projeler","Haberler"};
    int[] icons = {R.drawable.ic_projects,R.drawable.ic_news};


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = HomeListRowHolder_.build(context);
        } else {
            viewHolder = (HomeListRowHolder) convertView;
        }

        viewHolder.bind(icons[position],titles[position]);

        return viewHolder;
    }


}
