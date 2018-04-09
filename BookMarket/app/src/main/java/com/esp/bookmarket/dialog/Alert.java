package com.esp.bookmarket.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Alert {


    public static void alert(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton("OK", (dialogInterface, i) -> dialogInterface.cancel())
                .show();
    }

    public static void alert(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

}
