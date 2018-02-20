package com.esp.bookmarket.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper{

    private static final String DB_NAME = "book_market";
    private static final String TABLE_NAME = "Table";
    private static final String ID = "Id";

    public AppDatabase(Context context) {
        super(context, DB_NAME, null, 1);
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTO INCREMENT, "
//                + ""
                + ")";

    }

    public void insert() {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        database.insert(TABLE_NAME, null, values);
    }

    public void update() {
        SQLiteDatabase database = getWritableDatabase();
//        database.update()
    }

    public void delete() {

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
