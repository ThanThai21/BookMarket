package com.esp.bookmarket.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.esp.bookmarket.R;
import com.esp.bookmarket.adapters.EventAdapter;
import com.esp.bookmarket.model.Event;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.EventResponse;
import com.esp.bookmarket.network.service.EventService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView eventRecyclerView;
    private List<Event> eventList = new ArrayList<>();
    private EventAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        initViews();
        fetchData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        eventRecyclerView = findViewById(R.id.event_recycler_view);
        adapter = new EventAdapter(this, eventList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        eventRecyclerView.setAdapter(adapter);
        eventRecyclerView.setLayoutManager(layoutManager);
        toolbar.setNavigationOnClickListener(v -> finish());
        adapter.setOnItemClickListener(l -> Toast.makeText(this, "Comming soon", +Toast.LENGTH_SHORT).show());
    }

    private void fetchData() {
        API.createService(EventService.class)
                .getListEvent("https://raw.githubusercontent.com/ThanThai21/FakeAPI/master/book_market_events.json")
                .enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                        EventResponse body = response.body();
                        if (body == null || body.getEvents() == null || body.getEvents().isEmpty()) {
                            Toast.makeText(EventActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        eventList.addAll(body.getEvents());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<EventResponse> call, Throwable t) {
                        Toast.makeText(EventActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
