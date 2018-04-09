package com.esp.bookmarket.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.esp.bookmarket.R;
import com.esp.bookmarket.utils.AppUtils;

public class SearchActivity extends AppCompatActivity {

    private EditText titleEdt;
    private EditText authorEdt;
    private EditText publisherEdt;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();

    }

    private void initViews() {
        titleEdt = findViewById(R.id.search_titleEdt);
        authorEdt = findViewById(R.id.search_author);
        publisherEdt = findViewById(R.id.search_publisher);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void search(View view) {
        boolean isValidTitle = AppUtils.checkValidString(titleEdt.getText().toString());
        boolean isValidAuthor = AppUtils.checkValidString(authorEdt.getText().toString());
        boolean isValidPublisher = AppUtils.checkValidString(publisherEdt.getText().toString());
        Intent intent = new Intent(this, SearchResultActivity.class);
        startActivity(intent);
    }
}
