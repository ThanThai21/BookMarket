package com.esp.bookmarket.ui;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.esp.bookmarket.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView toolbarTitle;
    private RoundedImageView recommendedImage;
    private TextView recommendTitle;
    private TextView recommendAuthor;
    private TextView recommendPublisher;
    private TextView recommnedPrice;
    private TextView recommendSeller;
    private RecyclerView newReleaseRecyclerView;
    private RecyclerView categoryRecyclerView;
    private LinearLayoutManager layoutManager;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.home_drawer_layout);
        toolbarTitle = findViewById(R.id.toolbar_title);
        recommendedImage = findViewById(R.id.recommended_book_image);
        recommendTitle = findViewById(R.id.recommended_book_title);
        recommendAuthor = findViewById(R.id.recommended_book_author);
        recommendPublisher = findViewById(R.id.recommended_book_publisher);
        recommnedPrice = findViewById(R.id.recommended_book_price);
        recommendSeller = findViewById(R.id.recommended_book_seller);
        newReleaseRecyclerView = findViewById(R.id.home_new_release_recycler_view);
        categoryRecyclerView = findViewById(R.id.home_category_recycler_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new CategoryAdapter(this);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(adapter);
    }

    public void onMenuClick(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void onSearchClick(View view) {
        //navigate Search screen
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void onNotificationClick(View view) {
        //navigate notification screen
    }

    public void onRecommendedBooksClick(View view) {
        //navigate book detail
        Intent intent = new Intent(this, BookDetailActivity.class);
        startActivity(intent);
    }
}
