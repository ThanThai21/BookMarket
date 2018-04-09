package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.EditProfileRequest;
import com.esp.bookmarket.network.model.EditProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface EditProfileService {

    @PUT("/edit_profile")
    Call<EditProfileResponse> editProfile(@Body EditProfileRequest body);
}
