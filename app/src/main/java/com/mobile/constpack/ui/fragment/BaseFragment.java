package com.mobile.constpack.ui.fragment;

import android.app.Fragment;

import com.mobile.constpack.ui.activity.BaseController;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EFragment
public abstract class BaseFragment extends Fragment {

    @AfterViews
    public abstract void initViews();

    public BaseController getBaseController(){
        return (BaseController)getActivity();
    }
}
