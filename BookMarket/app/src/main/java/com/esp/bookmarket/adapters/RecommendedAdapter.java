package com.esp.bookmarket.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esp.bookmarket.R;
import com.esp.bookmarket.listeners.OnItemClickListener;
import com.esp.bookmarket.model.Book;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendViewHolder>{

    private Context context;
    private List<Book> bookList;
    private OnItemClickListener onItemClickListener;

    public RecommendedAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recommended_book, parent, false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        Book book = bookList.get(position);
        if (book.getImage() != null) {
            Glide.with(context).load(book.getImage()).into(holder.recommendImage);
        }
        holder.recommendTitle.setText(book.getTitle());
        holder.recommendAuthor.setText(book.getAuthor());
        holder.recommendPublisher.setText(book.getPublisher());
        holder.recommendPrice.setText(book.getPrice() + ".000 VNĐ");
        holder.recommendSeller.setText(book.getSeller());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        ImageView recommendImage;
        TextView recommendTitle;
        TextView recommendAuthor;
        TextView recommendPublisher;
        TextView recommendPrice;
        TextView recommendSeller;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            recommendImage = itemView.findViewById(R.id.recommended_book_image);
            recommendTitle = itemView.findViewById(R.id.recommended_book_title);
            recommendAuthor = itemView.findViewById(R.id.recommended_book_author);
            recommendPublisher = itemView.findViewById(R.id.recommended_book_publisher);
            recommendPrice = itemView.findViewById(R.id.recommended_book_price);
            recommendSeller = itemView.findViewById(R.id.recommended_book_seller);
        }
    }
}
