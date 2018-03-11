package com.esp.bookmarket.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.esp.bookmarket.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEdt;
    private EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
