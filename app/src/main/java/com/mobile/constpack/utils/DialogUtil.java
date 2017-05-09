package com.mobile.constpack.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.mobile.constpack.R;
import com.mobile.constpack.ui.dialogs.AlertDialogBuilder;

/**
 * Created by buraksoykal on 09/05/2017.
 */

public class DialogUtil {

    public static void showCommonAlertDialog(Context context, String title, String message) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(context.getString(R.string.dialog_ok), null).show();
    }

    public static void showCommonAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(context.getString(R.string.dialog_ok), listener).show();
    }

    public static void showCommonAlertDialog(Context context, String title, String message, DialogInterface.OnDismissListener listener) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(context.getString(R.string.dialog_ok), null).setOnDismissListener(listener).show();
    }

    public static void showCommonAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener negListener) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(context.getString(R.string.dialog_ok), listener).setNegativeButton(context.getString(R.string.dialog_cancel),negListener).show();
    }

    public static void showCommonYesNoAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener negListener) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(context.getString(R.string.dialog_yes), listener).setNegativeButton(context.getString(R.string.dialog_no),negListener).show();
    }

}
