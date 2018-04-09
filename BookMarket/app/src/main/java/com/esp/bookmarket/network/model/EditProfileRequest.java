package com.esp.bookmarket.network.model;

import com.esp.bookmarket.model.Profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileRequest {

    @SerializedName("/access_token")
    @Expose
    public String accessToken;

    public Profile profile;
}
