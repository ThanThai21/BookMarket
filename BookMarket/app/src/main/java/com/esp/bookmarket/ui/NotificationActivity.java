package com.esp.bookmarket.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.esp.bookmarket.R;
import com.esp.bookmarket.adapters.NotificationAdapter;
import com.esp.bookmarket.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private NotificationAdapter adapter;
    private List<Notification> notificationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();
        fetchData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.notification_recycler_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        notificationList = new ArrayList<>();
        adapter = new NotificationAdapter(this, notificationList);
        recyclerView.setAdapter(adapter);
    }

    public void fetchData() {
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
        notificationList.add(new Notification("ABC", "17:00", "06/04/2016"));
    }

    public void fillData() {

    }
}
