package com.mobile.constpack.ui.fragment;

import android.widget.ListView;
import android.widget.Toast;

import com.mobile.constpack.R;
import com.mobile.constpack.base.ConstPack;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.response.ProjectsResponse;
import com.mobile.constpack.ui.activity.NewsActivity_;
import com.mobile.constpack.ui.activity.ProjectController_;
import com.mobile.constpack.ui.adapters.HomeListAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    @ViewById(R.id.list_home)
    ListView listView;

    @Bean
    HomeListAdapter homeListAdapter;


    @Override
    public void initViews() {
         listView.setAdapter(homeListAdapter);
    }

    @ItemClick(R.id.list_home)
    void myListItemPositionClicked(int position) {
        switch (position){
            case 0 :
                getBaseController().showLoadingDialog();
                RestManager.getInstance().requestProjects(ConstPack.get().getUserId()).enqueue(new BaseCallback<ProjectsResponse>(getBaseController()) {
                    @Override
                    public void onSuccess(ProjectsResponse response) {
                        getBaseController().dismissLoadingDialog();
                        ProjectController_.intent(getBaseController()).projectsResponse(response).start();
                    }

                    @Override
                    public void onFailure(int errorId, String error) {
                        getBaseController().dismissLoadingDialog();
                        Toast.makeText(getBaseController(), "Error! :: " + error, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case 1 :
                NewsActivity_.intent(getBaseController()).start();
                break;
            default:
                break;
        }
    }
}
