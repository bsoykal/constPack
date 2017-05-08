package com.mobile.constpack.ui.activity;

import com.mobile.constpack.R;
import com.mobile.constpack.network.domain.Project;
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
    Project[] projects;

    @Override
    public void createViews() {
        if(projects.length==1){
            //TODO start PROJECT_PAGE_FRAGMENT
        }else{
            addFragmentToStack(ProjectListFragment_.builder().build(),true);
        }
    }
}
