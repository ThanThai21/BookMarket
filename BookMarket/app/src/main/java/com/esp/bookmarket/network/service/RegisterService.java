package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.RegisterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterService {

    @FormUrlEncoded
    @POST("/register")
    Call<RegisterResponse> register(@FieldMap Map<String, String> body);
}
