package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;

public interface LogoutService {

    @POST("/logout")
    Call<LogoutResponse> logout(@Field("access_token") String accessToken);

}
