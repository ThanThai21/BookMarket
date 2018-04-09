package com.esp.bookmarket.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esp.bookmarket.R;
import com.esp.bookmarket.dialog.LoadingDialog;
import com.esp.bookmarket.model.Book;
import com.esp.bookmarket.model.Profile;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.BookDetailResponse;
import com.esp.bookmarket.network.model.BookResponse;
import com.esp.bookmarket.network.service.BookDetailService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView sellerImage;
    private TextView sellerName;
    private TextView sellerPhone;
    private TextView sellerEmail;
    private TextView sellerAddress;

    private ImageView bookImage;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookCategory;
    private TextView bookPublisher;
    private TextView bookYear;
    private TextView bookQuality;
    private TextView bookQuantity;
    private TextView bookAbstract;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initViews();
        fetchData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);

        sellerImage = findViewById(R.id.seller_image);
        sellerName = findViewById(R.id.seller_name);
        sellerPhone = findViewById(R.id.seller_phone);
        sellerEmail = findViewById(R.id.seller_email);
        sellerAddress = findViewById(R.id.seller_address);

        bookImage = findViewById(R.id.book_image);
        bookTitle = findViewById(R.id.book_title);
        bookAuthor = findViewById(R.id.book_author);
        bookPublisher = findViewById(R.id.book_publisher);
        bookCategory = findViewById(R.id.book_category);
        bookQuality = findViewById(R.id.book_quality);
        bookQuantity = findViewById(R.id.book_quantity);
        bookAbstract = findViewById(R.id.book_abstract);
        bookYear = findViewById(R.id.book_year);

        toolbar.setNavigationOnClickListener(l -> finish());
        loadingDialog = new LoadingDialog(this);
    }

    public void fetchData() {
        loadingDialog.show();
        API.createService(BookDetailService.class)
                .getBookDetail("https://github.com/ThanThai21/FakeAPI/raw/master/book_market_book_detail.json")
                .enqueue(new Callback<BookDetailResponse>() {
                    @Override
                    public void onResponse(Call<BookDetailResponse> call, Response<BookDetailResponse> response) {
                        BookDetailResponse body = response.body();
                        fillData(body);
                        loadingDialog.cancel();
                    }

                    @Override
                    public void onFailure(Call<BookDetailResponse> call, Throwable t) {
                        loadingDialog.cancel();
                    }
                });
    }

    private void fillData(BookDetailResponse response) {

        Profile seller = response.seller;
        Book book = response.book;

        //seller
        sellerName.setText(seller.username);
        sellerPhone.setText(seller.phone);
        if (seller.avatar != null) {
            Glide.with(this).load(seller.avatar).into(sellerImage);
        }
        sellerAddress.setText(seller.address);
        sellerEmail.setText(seller.email);


        //book
        bookTitle.setText(book.getTitle());
        bookAbstract.setText(book.getBookAbstract());
        bookAuthor.setText(book.getAuthor());
        bookCategory.setText(book.getCategory().title);
        bookPublisher.setText(book.getPublisher());
        bookYear.setText(book.getYear());
        bookQuality.setText(book.getQuality() == 1 ? "New" : "Old");
        bookQuantity.setText(book.getQuality() + "");
        if (book.getImage() != null) {
            Glide.with(this).load(book.getImage()).into(bookImage);
        }
    }

    public void searchByBookTitle(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("type", "title");
        intent.putExtra("value", ""); //title
        startActivity(intent);
    }

    public void searchByAuthor(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("type", "title");
        intent.putExtra("value", ""); //title
        startActivity(intent);
    }

    public void searchByPublisher(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("type", "title");
        intent.putExtra("value", ""); //title
        startActivity(intent);
    }
}
