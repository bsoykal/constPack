package com.mobile.constpack.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mobile.constpack.network.domain.Product;
import com.mobile.constpack.ui.holders.ProductListRowHolder;
import com.mobile.constpack.ui.holders.ProductListRowHolder_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lombok.Setter;

/**
 * Created by buraksoykal on 03/02/2017.
 */


@EBean
public class ProductListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @Setter
    Product[] products;

    @Override
    public int getCount() {
        return products.length;
    }

    @Override
    public Product getItem(int position) {
        return products[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductListRowHolder viewHolder;
        if (convertView == null) {
            viewHolder = ProductListRowHolder_.build(context);
        } else {
            viewHolder = (ProductListRowHolder) convertView;
        }

        viewHolder.bind(products[position].getUrunAdi());

        return viewHolder;
    }


}
