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

import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.adapters.BookCateAdapter;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookCateItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BookCategoriesActivity extends AppCompatActivity {

    // 1. Khai báo TẤT CẢ 6 RecyclerViews
    private RecyclerView recyclerFeatured;
    private RecyclerView recyclerNovel, recyclerChild, recyclerNonFic, recyclerShortStories, recyclerPoems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.book_cate_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Code Bottom Navigation (Giữ nguyên) ---
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_bookstore);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(BookCategoriesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.nav_library) {
                Intent intent = new Intent(BookCategoriesActivity.this, LibraryActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else return itemId == R.id.nav_bookstore;
        });


        recyclerFeatured = findViewById(R.id.recyclerView_featured);
        recyclerNovel = findViewById(R.id.rcNovelAndLiterature);
        recyclerChild = findViewById(R.id.recyclerView_child);
        recyclerNonFic = findViewById(R.id.recycler_nonFic);
        recyclerShortStories = findViewById(R.id.recycler_shortStories);
        recyclerPoems = findViewById(R.id.recycler_poems);


        // ----- Setup cho Featured Collection -----
        recyclerFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // (Bạn cần tạo FeaturedItem (Model) và FeaturedAdapter)
        BookCateAdapter featuredAdapter = new BookCateAdapter(this, getFeaturedItems());
        recyclerFeatured.setAdapter(featuredAdapter);


        // Novel and Literature
        recyclerNovel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookAdapter novelAdapter = new BookAdapter(this, getNovelBooks());
        recyclerNovel.setAdapter(novelAdapter);

        // Child and Teenager
        recyclerChild.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookAdapter childAdapter = new BookAdapter(this, getChildBooks());
        recyclerChild.setAdapter(childAdapter);

        // Non Fiction
        recyclerNonFic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookAdapter nonFicAdapter = new BookAdapter(this, getNonFicBooks());
        recyclerNonFic.setAdapter(nonFicAdapter);

        // Short Stories
        recyclerShortStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookAdapter shortStoriesAdapter = new BookAdapter(this, getShortStories());
        recyclerShortStories.setAdapter(shortStoriesAdapter);

        // Poems
        recyclerPoems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookAdapter poemsAdapter = new BookAdapter(this, getPoems());
        recyclerPoems.setAdapter(poemsAdapter);
    }

    private List<BookCateItem> getFeaturedItems() {
        List<BookCateItem> list = new ArrayList<>();
        list.add(new BookCateItem("Non fiction",R.drawable.ic_home_24));
        list.add(new BookCateItem("Non fiction",R.drawable.ic_library_24));
        list.add(new BookCateItem("Non fiction",R.drawable.ic_store_24));
        list.add(new BookCateItem("Non fiction",R.drawable.ic_search_24));
        return list;
    }

    private List<Book> getNovelBooks() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.ic_home_24, "Tiểu Thuyết 1", "Tác giả A"));
        list.add(new Book(R.drawable.ic_library_24, "Tiểu Thuyết 2", "Tác giả B"));
        return list;
    }

    private List<Book> getChildBooks() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.ic_store_24, "Truyện Thiếu Nhi", "Tác giả C"));
        return list;
    }

    private List<Book> getNonFicBooks() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.ic_search_24, "Sách Phi Hư Cấu", "Tác giả D"));
        return list;
    }

    private List<Book> getShortStories() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.ic_arrow_forward, "Truyện Ngắn 1", "Tác giả E"));
        return list;
    }

    private List<Book> getPoems() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(R.drawable.ic_home_24, "Thơ Ca", "Tác giả F"));
        return list;
    }

}

