package com.mobile.constpack.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mobile.constpack.R;
import com.mobile.constpack.ui.fragment.BaseFragment;
import com.mobile.constpack.ui.fragment.HomeFragment;
import com.mobile.constpack.ui.fragment.HomeFragment_;
import com.mobile.constpack.ui.fragment.ProfileFragment;
import com.mobile.constpack.ui.fragment.ProfileFragment_;
import com.mobile.constpack.ui.fragment.QRCodeFragment;
import com.mobile.constpack.ui.fragment.QRCodeFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

/**
 * Created by buraksoykal on 31/01/2017.
 */

@EActivity(R.layout.activity_dashboard)
public class DashBoardActivity extends BaseActivity {

    FragmentManager fragmentManager;

    @ViewById(R.id.dashboard_tabs)
    TabLayout tabLayout;

    private int selectedTabPosition = 1;
    private int[] tabIcons = {
            R.drawable.qr,
            R.drawable.home,
            R.drawable.constructor
    };

    private HomeFragment homeFragment = HomeFragment_.builder().build();
    private ProfileFragment profileFragment = ProfileFragment_.builder().build();
    private QRCodeFragment qrCodeFragment = QRCodeFragment_.builder().build();

    private TabLayout.Tab homeTab;
    private TabLayout.Tab profileTab;
    private TabLayout.Tab qrCodeTab;


    @ViewById(R.id._appimg_home_selected)
    AppCompatImageView home_selected;
    @ViewById(R.id._appimg_qr_selected)
    AppCompatImageView qr_selected;
    @ViewById(R.id._appimg_constructor_selected)
    AppCompatImageView constructor_selected;



    @Override
    public void initViews() {
        fragmentManager = getFragmentManager();
        addFragmentToStack(HomeFragment_.builder().build(),false);
        setupTabIcons();

    }

   public void addFragmentToStack(BaseFragment fragment,boolean popBefore){

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

    private void setupTabIcons() {
        View qrView = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout,null);
        AppCompatImageView qrImage = (AppCompatImageView) qrView.findViewById(R.id.custom_tab_img);
        qrImage.setImageResource(tabIcons[0]);

        View homeView = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout,null);
        AppCompatImageView homeImage = (AppCompatImageView) homeView.findViewById(R.id.custom_tab_img);
        homeImage.setImageResource(tabIcons[1]);

        View profileView = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout,null);
        AppCompatImageView profileImage = (AppCompatImageView) profileView.findViewById(R.id.custom_tab_img);
        profileImage.setImageResource(tabIcons[2]);

        qrCodeTab = tabLayout.newTab().setCustomView(qrView);
        homeTab = tabLayout.newTab().setCustomView(homeView);
        profileTab = tabLayout.newTab().setCustomView(profileView);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.blue300));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setSelectedTabIndicatorHeight(tabLayout.getMeasuredHeight());
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                selectedTabPosition = tab.getPosition();
                switch (selectedTabPosition){
                    case 0 : addFragmentToStack(qrCodeFragment,true);
                        animateViewVisibility(qr_selected,View.VISIBLE);
                        animateViewVisibility(home_selected,View.INVISIBLE);
                        animateViewVisibility(constructor_selected,View.INVISIBLE);
                        break;
                    case 1 : addFragmentToStack(homeFragment,true);
                        animateViewVisibility(qr_selected,View. INVISIBLE);
                        animateViewVisibility(home_selected,View.VISIBLE);
                        animateViewVisibility(constructor_selected,View.INVISIBLE);
                        break;
                    case 2 : addFragmentToStack(profileFragment,true);
                        animateViewVisibility(qr_selected,View.INVISIBLE);
                        animateViewVisibility(home_selected,View.INVISIBLE);
                        animateViewVisibility(constructor_selected,View.VISIBLE);
                        break;
                    default:
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(DashBoardActivity.this,"RePosition :: "+tab.getPosition(),Toast.LENGTH_LONG).show();
            }
        });

        tabLayout.addTab(qrCodeTab,0,false);
        tabLayout.addTab(homeTab,1,false);
        tabLayout.addTab(profileTab,2,false);
        tabLayout.setSelected(true);
        tabLayout.getTabAt(1).select();


    }

    public static void animateViewVisibility(final View view, final int visibility)
    {
        // cancel runnning animations and remove and listeners
        view.animate().cancel();
        view.animate().setListener(null);

        // animate making view visible
        if (visibility == View.VISIBLE)
        {
            view.animate().alpha(1f).start();
            view.setVisibility(View.VISIBLE);
        }
        // animate making view hidden (HIDDEN or INVISIBLE)
        else
        {
            view.animate().setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    view.setVisibility(visibility);
                }
            }).alpha(0f).start();
        }
    }


}
