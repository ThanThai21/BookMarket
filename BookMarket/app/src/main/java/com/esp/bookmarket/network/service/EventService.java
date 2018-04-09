package com.esp.bookmarket.network.service;

import com.esp.bookmarket.network.model.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EventService {

    @GET
    Call<EventResponse> getListEvent(@Url String url);

}
