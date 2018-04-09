package com.esp.bookmarket.network.model;

import com.esp.bookmarket.model.Profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    public String id;
    public Profile profile;

    @SerializedName("access_token")
    @Expose
    public String accessToken;
}
