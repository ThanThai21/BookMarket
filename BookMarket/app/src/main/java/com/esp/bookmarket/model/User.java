package com.esp.bookmarket.model;

public class User {
    private String id;
    private Profile profile;

    private static User instance;
    public static User getInstance() {
        return instance;
    }

    public static void init(String id, Profile profile) {
        instance = new User(id, profile);
    }

    private User(String id, Profile profile) {
        this.id = id;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
