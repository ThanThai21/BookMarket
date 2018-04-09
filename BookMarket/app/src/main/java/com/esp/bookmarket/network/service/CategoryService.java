package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CategoryService {

    @GET
    Call<CategoryResponse> getCategories(@Url String url);

}
