package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.LoginData;
import com.esp.bookmarket.network.model.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> login(@FieldMap Map<String, String> body);
}
