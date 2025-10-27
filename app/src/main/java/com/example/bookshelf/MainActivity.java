package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.adapters.ContinueAdapter;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.ItemContinueReading;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerContinue, recyclerDiscover, recyclerBestSeller;
    private NestedScrollView scrollViewHome;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scrollViewHome = findViewById(R.id.scrollView);
        recyclerContinue = findViewById(R.id.recyclerView_continueReading);
        recyclerBestSeller = findViewById(R.id.recyclerView_bestsellers);

        setupContinueRecycler(recyclerContinue, getContinueReadingBooks());
//        setupRecycler(recyclerDiscover, getDiscoverBooks());
        setupRecycler(recyclerBestSeller, getBestSellerBooks());

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true;
            }
            else if(itemId == R.id.nav_library){
                Intent intent = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(intent);
                finish();
            }
            else if (itemId == R.id.nav_bookstore){
                Intent intent = new Intent(MainActivity.this, BookCategoriesActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
//            else if(itemId == R.id.nav_search){
//                Intent intent = new Intent(LibraryActivity.this, SearchActivity.class);
//                startActivity(intent);
//                finish();
//                return true;
//            }
            return false;
        });

        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    private void setupContinueRecycler(RecyclerView recyclerView, List<ItemContinueReading> items) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new ContinueAdapter(this, items));
    }

    private void setupRecycler(RecyclerView recyclerView, List<Book> books) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new BookAdapter(this, books));
    }


    private List<ItemContinueReading> getContinueReadingBooks() {
        List<ItemContinueReading> list = new ArrayList<>();
        list.add(new ItemContinueReading(R.drawable.ic_arrow_forward, "Lịch sử phần mềm", "Tác giả Trần B", "15"));
        list.add(new ItemContinueReading(R.drawable.ic_home_24, "Lập trình Web", "Tác giả A", "20"));
        list.add(new ItemContinueReading(R.drawable.ic_library_24, "Tâm lý học", "Tác giả X", "55"));
        return list;
    }

//    private List<Book> getDiscoverBooks() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book(R.drawable.ic_store_24, "Vũ Trụ Không Giới Hạn", "Stephen Hawking"));
//        list.add(new Book(R.drawable.ic_search_24, "Deep Learning A-Z", "Andrew Ng"));
//        list.add(new Book(R.drawable.ic_arrow_forward, "Nghệ Thuật Thiết Kế", "Dieter Rams"));
//        return list;
//    }

    private List<Book> getBestSellerBooks() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.ic_home_24, "Top 1: 100 Năm Cô Đơn", "Gabriel Márquez"));
        list.add(new Book(R.drawable.ic_library_24, "Top 2: Đắc Nhân Tâm", "Dale Carnegie"));
        list.add(new Book(R.drawable.ic_store_24, "Top 3: Nhà Giả Kim", "Paulo Coelho"));
        return list;
    }
}