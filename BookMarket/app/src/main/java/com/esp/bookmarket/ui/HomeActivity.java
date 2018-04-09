package com.esp.bookmarket.ui;

import android.app.Notification;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esp.bookmarket.R;
import com.esp.bookmarket.adapters.BookAdapter;
import com.esp.bookmarket.adapters.RecommendedAdapter;
import com.esp.bookmarket.data.Auth;
import com.esp.bookmarket.dialog.Alert;
import com.esp.bookmarket.helpers.StartSnapHelper;
import com.esp.bookmarket.model.Book;
import com.esp.bookmarket.model.Category;
import com.esp.bookmarket.model.Profile;
import com.esp.bookmarket.model.User;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.BookResponse;
import com.esp.bookmarket.network.model.CategoryResponse;
import com.esp.bookmarket.network.model.GetProfileResponse;
import com.esp.bookmarket.network.model.LoginResponse;
import com.esp.bookmarket.network.model.LogoutResponse;
import com.esp.bookmarket.network.model.ProfileData;
import com.esp.bookmarket.network.service.CategoryService;
import com.esp.bookmarket.network.service.GetBookList;
import com.esp.bookmarket.network.service.GetNewRelease;
import com.esp.bookmarket.network.service.GetProfileService;
import com.esp.bookmarket.network.service.LogoutService;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private TextView toolbarTitle;

    private RecyclerView recommendRecyclerView;
    private LinearLayoutManager recommendedLayoutManager;
    private List<Book> recommendedBookList = new ArrayList<>();
    private RecommendedAdapter recommendedAdapter;

    private RecyclerView newReleaseRecyclerView;
    private List<Book> newReleaseBookList = new ArrayList<>();
    private BookAdapter newReleaseBookAdapter;
    private LinearLayoutManager newReleaseLayoutManager;

    private RecyclerView categoryRecyclerView;
    private LinearLayoutManager categoryLayoutManager;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList = new ArrayList<>();

    //side bar
    private NavigationView navigationView;
    private View headerView;
    private TextView usernameHeaderView;
    private CircleImageView avatarHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        fetchData();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.home_drawer_layout);
        toolbarTitle = findViewById(R.id.toolbar_title);

        recommendRecyclerView = findViewById(R.id.recommended_book_recycler_view);
        recommendedLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedAdapter = new RecommendedAdapter(this, recommendedBookList);
        recommendRecyclerView.setLayoutManager(recommendedLayoutManager);
        recommendRecyclerView.setAdapter(recommendedAdapter);
        SnapHelper recommendSnapHelper = new LinearSnapHelper();
        recommendSnapHelper.attachToRecyclerView(recommendRecyclerView);
        recommendedAdapter.setOnItemClickListener(l -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            startActivity(intent);
        });

        categoryRecyclerView = findViewById(R.id.home_category_recycler_view);
        categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(l -> {
            Intent intent = new Intent(this, SearchResultActivity.class);
            startActivity(intent);
        });

        newReleaseRecyclerView = findViewById(R.id.home_new_release_recycler_view);
        newReleaseBookAdapter = new BookAdapter(this, newReleaseBookList);
        newReleaseLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        newReleaseRecyclerView.setAdapter(newReleaseBookAdapter);
        newReleaseRecyclerView.setLayoutManager(newReleaseLayoutManager);
        StartSnapHelper newReleaseSnapHelper = new StartSnapHelper();
        newReleaseSnapHelper.attachToRecyclerView(newReleaseRecyclerView);
        newReleaseBookAdapter.setOnItemClickListener(l -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            startActivity(intent);
        });

        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);
        usernameHeaderView = headerView.findViewById(R.id.header_username);
        avatarHeaderView = headerView.findViewById(R.id.header_avatar);

    }

    private void fillData() {
        User user = User.getInstance();
        if (user == null || user.getProfile() == null) {
            return;
        }
        Profile profile = user.getProfile();
        if (profile.avatar != null) {
            Glide.with(this).load(profile.avatar).into(avatarHeaderView);
        }
        usernameHeaderView.setText(profile.username);
    }

    public void fetchData() {
        String uid = Auth.getInstance(this).getUserId();
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
                        User.init(uid, data.profile);
                        fillData();
                    }

                    @Override
                    public void onFailure(Call<GetProfileResponse> call, Throwable t) {

                    }
                });

        API.createService(GetBookList.class)
                .getNewReleaseList("https://raw.githubusercontent.com/ThanThai21/FakeAPI/master/book_market_new_release.json")
                .enqueue(new Callback<BookResponse>() {
                    @Override
                    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                        BookResponse body = response.body();
                        if (body == null || body.bookList == null) {
                            Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        newReleaseBookList.addAll(body.bookList);
                        newReleaseBookAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        API.createService(GetBookList.class)
                .getRecommendList("https://raw.githubusercontent.com/ThanThai21/FakeAPI/master/book_market_new_release.json")
                .enqueue(new Callback<BookResponse>() {
                    @Override
                    public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                        BookResponse body = response.body();
                        if (body == null || body.bookList == null) {
                            Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        recommendedBookList.addAll(body.bookList);
                        recommendedAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<BookResponse> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        API.createService(CategoryService.class)
                .getCategories("https://raw.githubusercontent.com/ThanThai21/FakeAPI/master/book_market_categories.json")
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                        CategoryResponse body = response.body();
                        if (body != null) {
                            categoryList.addAll(body.categories);
                            categoryAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillData();
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
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", Auth.getInstance(this).getUserId());
            startActivity(intent);
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_event) {
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_logout) {
            API.createService(LogoutService.class).logout(Auth.getInstance(this).getAccessToken())
                    .enqueue(new Callback<LogoutResponse>() {
                        @Override
                        public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                            LogoutResponse body = response.body();
                            if (body == null || body.code != 1000) {
                                Alert.alert(HomeActivity.this, "Logout failed");
                                return;
                            }
                            Auth.getInstance(HomeActivity.this).logout();
                        }

                        @Override
                        public void onFailure(Call<LogoutResponse> call, Throwable t) {
                            Alert.alert(HomeActivity.this, "Logout failed");
                        }
                    });
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
