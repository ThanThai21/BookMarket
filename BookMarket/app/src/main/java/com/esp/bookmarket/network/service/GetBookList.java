package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetBookList {

    @GET
    Call<BookResponse> getNewReleaseList(@Url String url);

    @GET
    Call<BookResponse> getRecommendList(@Url String url);

    @GET
    Call<BookResponse> getMyBook(@Url String url);
}
