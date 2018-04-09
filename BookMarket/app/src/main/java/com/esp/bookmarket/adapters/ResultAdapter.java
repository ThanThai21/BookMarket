package com.esp.bookmarket.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esp.bookmarket.R;
import com.esp.bookmarket.model.Book;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>{

    private Context context;
    private List<Book> bookList;

    public ResultAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        Book book = bookList.get(position);

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView author;
        TextView price;

        public ResultViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.book_image);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            price = itemView.findViewById(R.id.book_price);

        }
    }
}
