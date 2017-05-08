package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.network.domain.Block;
import com.mobile.constpack.ui.holders.BlockListRowHolder;
import com.mobile.constpack.ui.holders.BlockListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class BlockListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @Setter
    Block[] blocks;

    @Override
    public int getCount() {
        return blocks.length;
    }

    @Override
    public Block getItem(int position) {
        return blocks[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BlockListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = BlockListRowHolder_.build(context);
        } else {
            viewHolder = (BlockListRowHolder) convertView;
        }

        viewHolder.bind(blocks[position].getBlokAdi());

        return viewHolder;
    }


}
