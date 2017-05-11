package com.mobile.constpack.ui.activity;

import com.mobile.constpack.R;
import com.mobile.constpack.network.response.ProjectsResponse;
import com.mobile.constpack.ui.fragment.ProjectListFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import lombok.Getter;

/**
 * Created by buraksoykal on 04/02/2017.
 */

@EActivity(R.layout.activity_project_controller)
public class ProjectController extends BaseController {

    @Getter
    @Extra
    ProjectsResponse projectsResponse;

    @Override
    public void createViews() {
        if(projectsResponse.getProjects().length==1){
            //TODO start PROJECT_PAGE_FRAGMENT
        }else{
            addFragmentToStack(ProjectListFragment_.builder().build(),true);
        }
    }
}
