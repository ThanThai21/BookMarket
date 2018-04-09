package com.esp.bookmarket.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.esp.bookmarket.R;
import com.esp.bookmarket.adapters.ResultAdapter;
import com.esp.bookmarket.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Book> bookList = new ArrayList<>();
    private ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        
        initViews();
        fetchData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.search_result_recycler_view);
        layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ResultAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void fetchData() {
        adapter.notifyDataSetChanged();
    }

    private void fillData() {

    }
}
