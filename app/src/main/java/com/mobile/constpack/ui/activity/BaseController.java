package com.mobile.constpack.ui.activity;

import android.app.FragmentManager;
import android.view.MenuItem;

import com.mobile.constpack.R;
import com.mobile.constpack.ui.fragment.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EActivity
public abstract class BaseController extends BaseActivity {

    FragmentManager fragmentManager;

    public abstract void createViews();

    @AfterViews
    public void initViews(){
        fragmentManager = getFragmentManager();
        createViews();
    }



    public void addFragmentToStack(BaseFragment fragment, boolean popBefore){
        if(popBefore && fragmentManager.getBackStackEntryCount()!=0)fragmentManager.popBackStack();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(fragment.getTag()).commit();
    }

    public void removeAllFromStack(){
        if (fragmentManager.getBackStackEntryCount()>0){
            do{
                fragmentManager.popBackStack();
            }while (fragmentManager.getBackStackEntryCount()!=0);
        }
    }

    @Override
    public void onBackPressed(){
        if(fragmentManager.getBackStackEntryCount()==1){
            finish();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
