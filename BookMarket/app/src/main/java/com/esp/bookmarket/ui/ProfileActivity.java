package com.esp.bookmarket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esp.bookmarket.R;
import com.esp.bookmarket.adapters.BookAdapter;
import com.esp.bookmarket.helpers.StartSnapHelper;
import com.esp.bookmarket.model.Book;
import com.esp.bookmarket.model.Profile;
import com.esp.bookmarket.model.User;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.BookResponse;
import com.esp.bookmarket.network.model.GetProfileResponse;
import com.esp.bookmarket.network.model.ProfileData;
import com.esp.bookmarket.network.service.GetBookList;
import com.esp.bookmarket.network.service.GetProfileService;
import com.esp.bookmarket.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView avatar;
    private TextView usernameView;
    private TextView phoneView;
    private TextView emailView;
    private TextView addressView;

    private RecyclerView myBookRecyclerView;
    private LinearLayoutManager layoutManager;
    private BookAdapter adapter;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        fetchData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews() {
        avatar = findViewById(R.id.avatar);
        usernameView = findViewById(R.id.profile_name);
        phoneView = findViewById(R.id.profile_phone);
        emailView = findViewById(R.id.profile_email);
        addressView = findViewById(R.id.profile_address);

        myBookRecyclerView = findViewById(R.id.my_book_list);
        adapter = new BookAdapter(this, bookList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        StartSnapHelper snapHelper = new StartSnapHelper();
        myBookRecyclerView.setAdapter(adapter);
        myBookRecyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(myBookRecyclerView);
    }

    public void fetchData() {
        String uid = getIntent().getStringExtra("user_id");
        API.createService(GetProfileService.class).getProfile(uid)
                .enqueue(new Callback<GetProfileResponse>() {
                    @Override
                    public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                        GetProfileResponse body = response.body();
                        if (body == null) {
                            return;
                        }
                        ProfileData data = body.data;
                        if (data == null) {
                            return;
                        }
                        fillData(data.profile);
                    }

                    @Override
                    public void onFailure(Call<GetProfileResponse> call, Throwable t) {

                    }
                });
        API.createService(GetBookList.class)
                .getMyBook("https://raw.githubusercontent.com/ThanThai21/FakeAPI/master/book_market_new_release.json")
                .enqueue(new Callback<BookResponse>() {
                    @Override
                    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                        BookResponse body = response.body();
                        if (body != null) {
                            bookList.addAll(body.bookList);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {

                    }
                });
    }

    public void fillData(Profile profile) {
        if (profile.avatar != null) {
            Glide.with(this).load(profile.avatar).into(avatar);
        }
        usernameView.setText(profile.username);
        if (AppUtils.checkValidString(profile.phone)) {
            phoneView.setText(profile.phone);
        } else {
            phoneView.setVisibility(View.GONE);
        }
        if (AppUtils.checkValidString(profile.email)) {
            emailView.setText(profile.email);
        } else {
            emailView.setVisibility(View.GONE);
        }
        if (AppUtils.checkValidString(profile.address)) {
            addressView.setText(profile.address);
        } else {
            addressView.setVisibility(View.GONE);
        }
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void onBack(View view) {
        finish();
    }
}
