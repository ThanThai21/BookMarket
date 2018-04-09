package com.esp.bookmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    public int id;

    @SerializedName("category")
    @Expose
    public String title;

    public String image;
}
