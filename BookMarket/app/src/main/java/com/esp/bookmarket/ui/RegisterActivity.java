package com.esp.bookmarket.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.esp.bookmarket.R;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.RegisterResponse;
import com.esp.bookmarket.network.service.RegisterService;
import com.esp.bookmarket.utils.AppUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEdt;
    private EditText usernameEdt;
    private EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
    }

    private void initViews() {
        emailEdt = findViewById(R.id.register_email);
        usernameEdt = findViewById(R.id.register_user_name);
        passwordEdt = findViewById(R.id.register_password);
    }

    public void register(View view) {
        String email = emailEdt.getText().toString();
        String username = usernameEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        boolean isValidEmail = AppUtils.isValidEmail(email);
        boolean isValidUsername = AppUtils.checkValidString(username);
        boolean isValidPassword = AppUtils.checkValidString(password);
        if (!isValidEmail) {
            //
        } else if (!isValidUsername) {
            //
        } else if (!isValidPassword) {

        }
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("username", username);
        API.createService(RegisterService.class).register(body)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });
    }
}
