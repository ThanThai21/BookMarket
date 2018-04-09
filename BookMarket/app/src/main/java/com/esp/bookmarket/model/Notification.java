package com.esp.bookmarket.model;

public class Notification {

    private String content;
    private String time;
    private String date;

    public Notification(String content, String time, String date) {
        this.content = content;
        this.time = time;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
