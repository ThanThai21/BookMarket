package com.esp.bookmarket.data;

import android.content.Context;

public class Auth {

    private static final String AUTH = "auth";
    private static final String USER_NAME = "username";
    private static final String ACCESS_TOKEN = "access_token";

    private static Auth instance;
    private Context context;

    public static Auth getInstance(Context context) {
        if (instance == null) {
            instance = new Auth(context);
        }
        return instance;
    }

    private Auth(Context context) {
        this.context = context;
    }

    public String getUsername() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .getString(USER_NAME, null);
    }

    public String getAccessToken() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .getString(ACCESS_TOKEN, null);
    }

    public void saveUser(String username, String accessToken) {
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .edit()
                .putString(USER_NAME, username)
                .putString(ACCESS_TOKEN, accessToken)
                .apply();
    }
}
