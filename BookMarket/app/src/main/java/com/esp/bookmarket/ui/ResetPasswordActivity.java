package com.esp.bookmarket.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.esp.bookmarket.R;
import com.esp.bookmarket.utils.AppUtils;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText emailEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initViews();
    }

    private void initViews() {
        emailEdt = findViewById(R.id.rp_email);
    }

    public void onBack(View view) {
        finish();
    }

    public void onResetPassword(View view) {
        String email = emailEdt.getText().toString();
        if (!AppUtils.isValidEmail(email)) {
            new AlertDialog.Builder(this)
                    .setMessage("Email is not valid")
                    .show();
        }
        //request server
    }
}
