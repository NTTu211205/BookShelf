package com.example.bookshelf.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshelf.BookClickedActivity;
import com.example.bookshelf.R;
import com.example.bookshelf.api.models.BookAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAPiAdapter extends RecyclerView.Adapter<PicksHolder> {
    List<BookAPI> books;
    Context context;

    public BookAPiAdapter(List<BookAPI> books,Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public PicksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items, parent, false);
        return new PicksHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicksHolder holder, int position) {
        BookAPI book = books.get(position);

        holder.textView_title.setText(book.getBookInfo().getTitle());

        String linkImage = null;
        if (book.getBookInfo() != null && book.getBookInfo().getImageLinks() != null) {
            linkImage = book.getBookInfo().getImageLinks().getThumnail();
        }
        if (linkImage != null) {
            linkImage = linkImage.replace("http://", "https://");
        }
        Glide.with(holder.itemView.getContext())
                .load(linkImage)
                .placeholder(R.drawable.icons8_loading_16)
                .error(R.drawable.non_thumbnail)
                .into(holder.imageView_book);

        holder.textView_author.setText(book.getBookInfo().getAuthors());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookClickedActivity.class);
                intent.putExtra("bookId", book.getId());
                v.getContext().startActivity(intent);
//                ((Activity) v.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
