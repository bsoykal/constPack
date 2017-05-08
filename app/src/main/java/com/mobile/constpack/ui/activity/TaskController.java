package com.mobile.constpack.ui.activity;

import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.mobile.constpack.R;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.domain.Task;
import com.mobile.constpack.network.request.UpdateTaskRequest;
import com.mobile.constpack.network.response.BaseResponse;
import com.mobile.constpack.ui.adapters.TaskListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import lombok.Getter;

/**
 * Created by buraksoykal on 04/02/2017.
 */

@EActivity(R.layout.activity_taskcontroller)
public class TaskController extends BaseActivity {

    @Getter
    @Extra
    Task[] tasks;

    @Getter
    @Extra
    boolean isEditable;


    @ViewById(R.id.task_listview)
    SwipeMenuListView swipeMenuListView;

    private SwipeMenuCreator creator;

    @Bean
    TaskListAdapter taskListAdapter;



    @AfterViews
    public void initViews() {
         creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());

                openItem.setBackground(new ColorDrawable(ContextCompat.getColor(TaskController.this,R.color.blue300)));


                switch (menu.getViewType()) {
                    case 0:
                        openItem.setIcon(ContextCompat.getDrawable(TaskController.this,R.drawable.ic_done_white));
                        break;
                    case 1:
                        openItem.setIcon(ContextCompat.getDrawable(TaskController.this,R.drawable.ic_cancel_white));
                        break;

                }

                openItem.setWidth(width/4);
                menu.addMenuItem(openItem);
            }
        };

// set creator
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, final SwipeMenu menu, int index) {
                showLoadingDialog();
                RestManager.getInstance().requestUpdateTask(new UpdateTaskRequest(tasks[position].getId(),menu.getViewType()==0)).enqueue(new BaseCallback<BaseResponse>(TaskController.this) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        dismissLoadingDialog();
                        tasks[position].setTamamlandi(menu.getViewType()==0);
                        taskListAdapter.setTasks(tasks);
                        taskListAdapter.notifyDataSetChanged();
                        swipeMenuListView.performClick();
                    }

                    @Override
                    public void onFailure(int errorId, String error) {
                        dismissLoadingDialog();
                        Toast.makeText(TaskController.this, "İstek gerçekleşmedi !!", Toast.LENGTH_SHORT).show();
                    }
                });
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        swipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        taskListAdapter.setTasks(tasks);

        swipeMenuListView.setAdapter(taskListAdapter);

        swipeMenuListView.setCloseInterpolator(new AccelerateDecelerateInterpolator());
        swipeMenuListView.setOpenInterpolator(new AccelerateDecelerateInterpolator());
    }
}
