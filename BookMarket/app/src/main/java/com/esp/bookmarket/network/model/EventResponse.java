package com.esp.bookmarket.network.model;

import com.esp.bookmarket.model.Event;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {

    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;


    public List<Event> getEvents() {
        return events;
    }

    public Integer getTotalCount() {
        return totalCount;
    }
}
