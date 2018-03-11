package com.esp.bookmarket.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class AppUtils {

    public static boolean checkValidString(String str) {
        return str != null && !str.isEmpty();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
