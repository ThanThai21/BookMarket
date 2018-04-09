package com.esp.bookmarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    private int id;
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String seller;
    private int quality;
    private int like;

    @SerializedName("total_comment")
    @Expose
    private int totalComment;
    private int price;
    private Category category;

    @SerializedName("abstract")
    @Expose
    private String bookAbstract;
    private String year;

    public Book(int id, String title, String author, String publisher, int quality, int like, int totalComment, int price, String seller) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quality = quality;
        this.like = like;
        this.totalComment = totalComment;
        this.price = price;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    public String getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getSeller() {
        return seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
