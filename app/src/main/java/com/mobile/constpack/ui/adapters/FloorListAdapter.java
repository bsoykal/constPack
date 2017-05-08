package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.network.domain.Floor;
import com.mobile.constpack.ui.holders.FloorListRowHolder;
import com.mobile.constpack.ui.holders.FloorListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class FloorListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @Setter
    Floor[] floors;

    @Override
    public int getCount() {
        return floors.length;
    }

    @Override
    public Floor getItem(int position) {
        return floors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FloorListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = FloorListRowHolder_.build(context);
        } else {
            viewHolder = (FloorListRowHolder) convertView;
        }

        viewHolder.bind(floors[position].getKatAdi());

        return viewHolder;
    }


}
