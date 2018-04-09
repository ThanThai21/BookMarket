package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.GetProfileResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetProfileService {

    @GET("/get_profile")
    Call<GetProfileResponse> getProfile(@Query("user_id") String userId);

}
