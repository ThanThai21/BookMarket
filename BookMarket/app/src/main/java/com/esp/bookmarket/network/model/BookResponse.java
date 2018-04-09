package com.esp.bookmarket.network.model;

import com.esp.bookmarket.model.Book;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {

    @SerializedName("total_count")
    @Expose
    public int totalCount;

    @SerializedName("data")
    @Expose
    public List<Book> bookList;

}
