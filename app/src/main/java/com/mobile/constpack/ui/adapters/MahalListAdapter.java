package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.network.domain.Mahal;
import com.mobile.constpack.ui.holders.MahalListRowHolder;
import com.mobile.constpack.ui.holders.MahalListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class MahalListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @Setter
    Mahal[] mahals;

    @Override
    public int getCount() {
        return mahals.length;
    }

    @Override
    public Mahal getItem(int position) {
        return mahals[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       MahalListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = MahalListRowHolder_.build(context);
        } else {
            viewHolder = (MahalListRowHolder) convertView;
        }

        viewHolder.bind(mahals[position].getMahalAdi());

        return viewHolder;
    }


}
