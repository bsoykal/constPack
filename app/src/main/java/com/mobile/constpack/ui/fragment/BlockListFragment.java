package com.mobile.constpack.ui.fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.mobile.constpack.R;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.domain.Block;
import com.mobile.constpack.network.response.FloorsResponse;
import com.mobile.constpack.ui.adapters.BlockListAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EFragment(R.layout.fragment_general_list)
public class BlockListFragment extends BaseFragment {

    @FragmentArg
    Block[] blocks;

    @ViewById(R.id.list_general)
    ListView listView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.toolbar_title)
    AppCompatTextView toolbar_title;

    @Bean
    BlockListAdapter blockListAdapter;


    @Override
    public void initViews() {
        initActionBar();
        blockListAdapter.setBlocks(blocks);
        listView.setAdapter(blockListAdapter);
    }

    private void initActionBar() {
        getBaseController().setSupportActionBar(toolbar);
        ActionBar actionBar = getBaseController().getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar_title.setText(R.string.block_list_toolbar_title);
    }

    @ItemClick(R.id.list_general)
    void myListItemPositionClicked(int position) {
        getBaseController().showLoadingDialog();
        RestManager.getInstance().requestFloors(blockListAdapter.getItem(position).getBlokId()).enqueue(new BaseCallback<FloorsResponse>(getBaseController()) {
            @Override
            public void onSuccess(FloorsResponse response) {
                getBaseController().dismissLoadingDialog();
                getBaseController().addFragmentToStack(FloorListFragment_.builder().floors(response.getFloors()).build(),false);
            }

            @Override
            public void onFailure(int errorId, String error) {
                getBaseController().dismissLoadingDialog();
                //TODO error dialog
            }
        });
    }


}
