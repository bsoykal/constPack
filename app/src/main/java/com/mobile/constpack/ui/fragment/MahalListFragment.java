package com.mobile.constpack.ui.fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.constpack.R;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.domain.Mahal;
import com.mobile.constpack.network.response.TasksResponse;
import com.mobile.constpack.ui.activity.TaskController_;
import com.mobile.constpack.ui.adapters.MahalListAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EFragment(R.layout.fragment_general_list)
public class MahalListFragment extends BaseFragment {

    @FragmentArg
    Mahal[] mahals;

    @ViewById(R.id.list_general)
    ListView listView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.toolbar_title)
    AppCompatTextView toolbar_title;

    @Bean
    MahalListAdapter mahalListAdapter;


    @Override
    public void initViews() {
        initActionBar();
        mahalListAdapter.setMahals(mahals);
        listView.setAdapter(mahalListAdapter);
    }

    private void initActionBar() {
        getBaseController().setSupportActionBar(toolbar);
        ActionBar actionBar = getBaseController().getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar_title.setText(R.string.mahal_list_toolbar_title);
    }

    @ItemClick(R.id.list_general)
    void myListItemPositionClicked(int position) {

        RestManager.getInstance().requestTasks(""+mahalListAdapter.getItem(position).getMahalId()).enqueue(new BaseCallback<TasksResponse>(getActivity()) {
            @Override
            public void onSuccess(TasksResponse response) {
                TaskController_.intent(getBaseController()).tasksResponse(response).isEditable(false).start();
            }

            @Override
            public void onFailure(int errorId, String error) {
                Toast.makeText(getBaseController(), "Error! :: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }


}
