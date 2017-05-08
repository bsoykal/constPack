package com.mobile.constpack.ui.fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.mobile.constpack.R;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.domain.Product;
import com.mobile.constpack.network.response.MahalsResponse;
import com.mobile.constpack.ui.adapters.ProductListAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EFragment(R.layout.fragment_general_list)
public class ProductListFragment extends BaseFragment {

    @FragmentArg
    Product[] products;

    @ViewById(R.id.list_general)
    ListView listView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.toolbar_title)
    AppCompatTextView toolbar_title;

    @Bean
    ProductListAdapter productListAdapter;


    @Override
    public void initViews() {
        initActionBar();
        productListAdapter.setProducts(products);
        listView.setAdapter(productListAdapter);
    }

    private void initActionBar() {
        getBaseController().setSupportActionBar(toolbar);
        ActionBar actionBar = getBaseController().getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar_title.setText(R.string.product_list_toolbar_title);
    }

    @ItemClick(R.id.list_general)
    void myListItemPositionClicked(int position) {
        getBaseController().showLoadingDialog();
        RestManager.getInstance().requestMahals(productListAdapter.getItem(position).getUrunId()).enqueue(new BaseCallback<MahalsResponse>(getBaseController()) {
            @Override
            public void onSuccess(MahalsResponse response) {
                getBaseController().dismissLoadingDialog();
                getBaseController().addFragmentToStack(MahalListFragment_.builder().mahals(response.getMahals()).build(),false);
            }

            @Override
            public void onFailure(int errorId, String error) {
                getBaseController().dismissLoadingDialog();
            }
        });
    }


}
