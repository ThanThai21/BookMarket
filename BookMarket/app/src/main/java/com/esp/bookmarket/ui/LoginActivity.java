package com.esp.bookmarket.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.esp.bookmarket.R;
import com.esp.bookmarket.data.Auth;
import com.esp.bookmarket.dialog.Alert;
import com.esp.bookmarket.model.User;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.LoginResponse;
import com.esp.bookmarket.network.service.LoginService;
import com.esp.bookmarket.network.model.LoginData;
import com.esp.bookmarket.utils.AppUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public EditText emailEdt;
    public EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        emailEdt = findViewById(R.id.login_email);
        passwordEdt = findViewById(R.id.login_password);
    }

    public void onForgotPasswordClick(View view) {
        //navigate reset password screen
    }

    public void onLoginClick(View view) {
        //login
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        boolean isValidUsername = AppUtils.isValidEmail(email);
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
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        API.createService(LoginService.class).login(body)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse body = response.body();
                        if (body == null) {
                            return;
                        }
                        LoginData data = body.data;
                        User.init(data.id, data.profile);
                        Auth.getInstance(LoginActivity.this).saveAccessToken(data.accessToken);
                        Auth.getInstance(LoginActivity.this).saveUserId(data.id);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Alert.alert(LoginActivity.this, "Error", "Login failed! Try again...");
                    }
                });
    }

    public void onRegisterClick(View view) {
        //navigate register screen
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
