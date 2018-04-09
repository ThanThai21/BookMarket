package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetNewRelease {

    @GET
    Call<BookResponse> getNewReleaseList(@Url String url);

}
