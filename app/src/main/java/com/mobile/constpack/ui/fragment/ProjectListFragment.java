package com.mobile.constpack.ui.fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.mobile.constpack.R;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.response.BlocksResponse;
import com.mobile.constpack.ui.activity.ProjectController;
import com.mobile.constpack.ui.adapters.ProjectListAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EFragment(R.layout.fragment_general_list)
public class ProjectListFragment extends BaseFragment {

    @ViewById(R.id.list_general)
    ListView listView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.toolbar_title)
    AppCompatTextView toolbar_title;

    @Bean
    ProjectListAdapter projectListAdapter;




    @Override
    public void initViews() {
        initActionBar();

        projectListAdapter.setProjects(((ProjectController)getBaseController()).getProjects());
        listView.setAdapter(projectListAdapter);
    }

    private void initActionBar() {
        getBaseController().setSupportActionBar(toolbar);
        ActionBar actionBar = getBaseController().getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar_title.setText(R.string.project_list_toolbar_title);
    }

    @ItemClick(R.id.list_general)
    void myListItemPositionClicked(int position) {
        getBaseController().showLoadingDialog();
        RestManager.getInstance().requestBlocks(projectListAdapter.getProjectId(position)).enqueue(new BaseCallback<BlocksResponse>(getBaseController()) {
            @Override
            public void onSuccess(BlocksResponse response) {
                getBaseController().dismissLoadingDialog();
                getBaseController().addFragmentToStack(BlockListFragment_.builder().blocks(response.getBlocks()).build(),false);
            }

            @Override
            public void onFailure(int errorId, String error) {
                getBaseController().dismissLoadingDialog();
                //TODO error dialog
            }
        });
    }


}
