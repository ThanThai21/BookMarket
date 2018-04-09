package com.esp.bookmarket.network.model;

import com.esp.bookmarket.model.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("total_count")
    @Expose
    public int totalCount;

    public List<Category> categories;
}
