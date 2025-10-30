package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.adapters.NearestAdapter;
import com.example.bookshelf.adapters.SearchTermAdapter; // Đã thêm
import com.example.bookshelf.models.NearestRead;
import com.example.bookshelf.models.NearestSearchItem; // Đã thêm
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rcNearest, rcRead, rcTrend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            } else if (itemId == R.id.nav_bookstore) {
                Intent intent = new Intent(SearchActivity.this, BookCategoriesActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.nav_library) {
                Intent intent = new Intent(SearchActivity.this, LibraryActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            return itemId == R.id.nav_search;
        });

        rcNearest = findViewById(R.id.rcNearest);
        rcRead = findViewById(R.id.rcRead);
//        rcTrend = findViewById(R.id.rcTrend);

        // --- Setup Nearest Search (VERTICAL list of search terms) ---
        rcNearest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SearchTermAdapter nearestAdapter = new SearchTermAdapter(this, getNearestSearchTerms());
        rcNearest.setAdapter(nearestAdapter);

        // --- Setup Nearest Read (HORIZONTAL list of NearestRead items) ---
        rcRead.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        NearestAdapter readAdapter = new NearestAdapter(this, getReadBooks());
        rcRead.setAdapter(readAdapter);

        // --- Setup Trending (VERTICAL list of search terms) ---
//        rcTrend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        SearchTermAdapter trendAdapter = new SearchTermAdapter(this, getTrendingSearchTerms());
//        rcTrend.setAdapter(trendAdapter);
    }

    // --- Phương thức tạo dữ liệu cho Nearest Search (List<NearestSearchItem>) ---
    private List<NearestSearchItem> getNearestSearchTerms() {
        List<NearestSearchItem> list = new ArrayList<>();
        list.add(new NearestSearchItem("sherlock holmes"));
        list.add(new NearestSearchItem("horror"));
        list.add(new NearestSearchItem("richard harding"));
        list.add(new NearestSearchItem("cinderella"));
        return list;
    }

    private List<NearestRead> getReadBooks() {
        List<NearestRead> list = new ArrayList<>();
        list.add(new NearestRead(R.drawable.ic_arrow_forward, "Tác giả Z", "Book", "Sách Đã Đọc"));
        list.add(new NearestRead(R.drawable.ic_home_24, "Tác giả A", "Book", "Sách Đã Đọc"));
        list.add(new NearestRead(R.drawable.ic_library_24, "Tác giả B", "Book", "Sách Đã Đọc"));
        return list;
    }

    private List<NearestSearchItem> getTrendingSearchTerms() {
        List<NearestSearchItem> list = new ArrayList<>();
        list.add(new NearestSearchItem("Tiểu thuyết mới"));
        list.add(new NearestSearchItem("Self-Help bán chạy"));
        list.add(new NearestSearchItem("Truyện tranh Nhật Bản"));
        list.add(new NearestSearchItem("Kinh tế 2024"));
        return list;
    }
}