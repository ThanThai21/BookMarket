package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.BookDetailResponse;
import com.esp.bookmarket.network.model.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BookDetailService {

    @GET
    Call<BookDetailResponse> getBookDetail(@Url String url);

}
