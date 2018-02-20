package com.esp.bookmarket.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esp.bookmarket.R;
import com.esp.bookmarket.data.Auth;
import com.esp.bookmarket.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    private Auth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = Auth.getInstance(this);
        boolean isValidUser = AppUtils.checkValidString(mAuth.getUsername()) && AppUtils.checkValidString(mAuth.getAccessToken());
        if (isValidUser) {
            //go to home screen
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            // go to login screen
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
