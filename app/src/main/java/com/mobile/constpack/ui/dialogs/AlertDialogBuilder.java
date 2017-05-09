package com.mobile.constpack.ui.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.mobile.constpack.ui.holders.DialogTitleViewHolder;

public class AlertDialogBuilder extends AlertDialog.Builder {


    public AlertDialogBuilder(Context context) {
        super(context);
    }

    public AlertDialogBuilder(Context context, int theme) {
        super(context, theme);
    }

    public AlertDialogBuilder(Context context, String customTitle) {
        super(context);
        DialogTitleViewHolder dialogTitleViewHolder = new DialogTitleViewHolder(context,customTitle);
        dialogTitleViewHolder.bind(customTitle);
    }
}
