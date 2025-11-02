package com.example.bookshelf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.database.models.SearchHistory;
import com.example.bookshelf.models.NearestSearchItem;

import java.util.List;

public class SearchTermAdapter extends RecyclerView.Adapter<SearchTermAdapter.SearchTermViewHolder> {

    private Context context;
    private List<SearchHistory> itemList;

    public SearchTermAdapter(Context context, List<SearchHistory> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SearchTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nearest_search_term_text, parent, false);
        return new SearchTermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTermViewHolder holder, int position) {
        SearchHistory item = itemList.get(position);
        holder.textViewSearchTerm.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateList(List<SearchHistory> newList) {
        this.itemList = newList;
        notifyDataSetChanged(); // Báo cho RecyclerView vẽ lại
    }

    public static class SearchTermViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSearchTerm;
        ImageView iconSearch, iconAction;

        public SearchTermViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSearchTerm = itemView.findViewById(R.id.textView_search_term);
            iconSearch = itemView.findViewById(R.id.icon_search);
            iconAction = itemView.findViewById(R.id.icon_action);
        }
    }
}