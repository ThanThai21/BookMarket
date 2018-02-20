package com.esp.bookmarket.data;


import android.content.Context;

public class AppPreferences {

    private static final String APP_PREFS = "book_market";

    private static void putInt(Context context, String key, int value) {
        context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
                .edit()
                .putInt(key, value)
                .apply();
    }

    private static void putString(Context context, String key, String value) {
        context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
                .edit().putString(key, value)
                .apply();
    }
}
