package com.esp.bookmarket.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.esp.bookmarket.R;

public class LoadingDialog extends Dialog{



    public LoadingDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.loading_dialog);
    }
}
