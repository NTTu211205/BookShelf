package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView; // Cần thêm import này

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.models.BookCateItem;
import com.example.bookshelf.R;

import java.util.List;

public class BookCateAdapter extends RecyclerView.Adapter<BookCateAdapter.BookCateViewHolder> {

    private Context context;
    private List<BookCateItem> bookList;

    public BookCateAdapter(Context context, List<BookCateItem> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookCateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CẬP NHẬT: Dùng R.layout.items_featured_col để match với book_cate_page.xml
        View view = LayoutInflater.from(context).inflate(R.layout.items_featured_col, parent, false);
        return new BookCateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookCateViewHolder holder, int position) {
        BookCateItem book = bookList.get(position);

        holder.coverImageView.setImageResource(book.getImageResId());
        // CẬP NHẬT: Hiển thị tiêu đề chính (lấy từ trường 'type')
        holder.textTitle.setText(book.getType());
        // textLabel sẽ giữ nguyên text mặc định trong XML là "FEATURED COLLECTION"
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookCateViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImageView;
        TextView textTitle, textLabel; // Đã thêm TextViews cho Title và Label

        public BookCateViewHolder(@NonNull View itemView) {
            super(itemView);
            // CẬP NHẬT ID: Ánh xạ tới ID trong items_featured_col.xml
            coverImageView = itemView.findViewById(R.id.imageView_featured_books);
            textTitle = itemView.findViewById(R.id.textView_featured_title);
            textLabel = itemView.findViewById(R.id.textView_featured_label);
        }
    }
}