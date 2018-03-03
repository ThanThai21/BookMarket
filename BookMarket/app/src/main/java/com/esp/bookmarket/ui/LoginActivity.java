package com.esp.bookmarket.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.esp.bookmarket.R;
import com.esp.bookmarket.utils.AppUtils;

public class LoginActivity extends AppCompatActivity {

    public EditText usernameEdt;
    public EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        usernameEdt = findViewById(R.id.login_user_name);
        passwordEdt = findViewById(R.id.login_password);
    }

    public void onForgotPasswordClick(View view) {
        //navigate reset password screen
    }

    public void onLoginClick(View view) {
        //login
        String username = usernameEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        boolean isValidUsername = AppUtils.checkValidString(username);
        boolean isValidPassword = AppUtils.checkValidString(password) && password.length() >= 8;
        if (!isValidUsername) {
            new AlertDialog.Builder(this)
                    .setMessage("Username is not valid")
                    .setNegativeButton("OK", (dialogInterface, i) -> dialogInterface.cancel())
                    .show();
            return;
        }
        if (!isValidPassword) {
            new AlertDialog.Builder(this)
                    .setMessage("Password is not valid")
                    .setNegativeButton("OK", ((dialogInterface, i) -> dialogInterface.cancel()))
                    .show();
            return;
        }
        //retrofit
    }

    public void onRegisterClick(View view) {
        //navigate register screen
    }
}
