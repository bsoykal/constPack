package com.mobile.constpack.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by buraksoykal on 09/05/2017.
 */

public class ScreenUtils {

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            return resources.getDimensionPixelSize(resourceId);
        else
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25f, resources.getDisplayMetrics());
    }
}
