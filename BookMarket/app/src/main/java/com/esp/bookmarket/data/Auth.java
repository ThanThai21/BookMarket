package com.esp.bookmarket.data;

import android.content.Context;

public class Auth {

    private static final String AUTH = "auth";
    private static final String USER_NAME = "username";
    private static final String USER_ID = "user_id";
    private static final String ACCESS_TOKEN = "access_token";

    private static Auth instance;
    private Context context;
    private String accessToken;

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

    public void saveUserId(String userId) {
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .edit()
                .putString(USER_ID, userId)
                .apply();
    }

    public String getUserId() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .getString(USER_ID, null);
    }

    public String getAccessToken() {
//        if (accessToken != null) {
//            return accessToken;
//        }
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .getString(ACCESS_TOKEN, null);
    }

    public void saveAccessToken(String accessToken) {
        this.accessToken = accessToken;
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .edit()
                .putString(ACCESS_TOKEN, accessToken)
                .apply();
    }

    public void saveUser(String username, String accessToken) {
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .edit()
                .putString(USER_NAME, username)
                .putString(ACCESS_TOKEN, accessToken)
                .apply();
    }

    public void logout() {
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .edit()
                .remove(ACCESS_TOKEN)
                .remove(USER_ID)
                .apply();
    }
}
