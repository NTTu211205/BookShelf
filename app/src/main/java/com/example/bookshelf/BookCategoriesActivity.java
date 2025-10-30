package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.adapters.BookAPiAdapter;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.adapters.BookCateAdapter;
import com.example.bookshelf.api.ApiClient;
import com.example.bookshelf.api.ApiService;
import com.example.bookshelf.api.models.BookAPI;
import com.example.bookshelf.api.response.BookApiResponse;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.models.BookCateItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookCategoriesActivity extends AppCompatActivity {
    private final String NOVELS = "subject:novels";
    private final String CHILD_AND_TEENAGER = "subject:child";
    private final String NON_FICTION = "subject:nonfiction";
    private final String SHORT_STORIES = "subject:shortstories";
    private final String POEMS = "subject:poems";
    private final ApiService api = ApiClient.getClient().create(ApiService.class);

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
            }
            else if(itemId == R.id.nav_search){
                Intent intent = new Intent(BookCategoriesActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            else return itemId == R.id.nav_bookstore;
        });


        recyclerFeatured = findViewById(R.id.recyclerView_featured);
        recyclerNovel = findViewById(R.id.rcNovelAndLiterature);
        recyclerChild = findViewById(R.id.recyclerView_child);
        recyclerNonFic = findViewById(R.id.recycler_nonFic);
        recyclerShortStories = findViewById(R.id.recycler_shortStories);
        recyclerPoems = findViewById(R.id.recycler_poems);


        // ----- Setup cho Featured Collection -----
        recyclerFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookCateAdapter featuredAdapter = new BookCateAdapter(this, getFeaturedItems());
        recyclerFeatured.setAdapter(featuredAdapter);

        // Novel and Literature
//        recyclerNovel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        BookAdapter novelAdapter = new BookAdapter(this, getNovelBooks());
//        recyclerNovel.setAdapter(novelAdapter);
        loadRecyclerView(NOVELS, recyclerNovel);

        // Child and Teenager
//        recyclerChild.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        BookAdapter childAdapter = new BookAdapter(this, getChildBooks());
//        recyclerChild.setAdapter(childAdapter);
        loadRecyclerView(CHILD_AND_TEENAGER, recyclerChild);

        // Non Fiction
//        recyclerNonFic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        BookAdapter nonFicAdapter = new BookAdapter(this, getNonFicBooks());
//        recyclerNonFic.setAdapter(nonFicAdapter);
        loadRecyclerView(NON_FICTION, recyclerNonFic);

        // Short Stories
//        recyclerShortStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        BookAdapter shortStoriesAdapter = new BookAdapter(this, getShortStories());
//        recyclerShortStories.setAdapter(shortStoriesAdapter);
        loadRecyclerView(SHORT_STORIES, recyclerShortStories);

        // Poems
//        recyclerPoems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        BookAdapter poemsAdapter = new BookAdapter(this, getPoems());
//        recyclerPoems.setAdapter(poemsAdapter);
        loadRecyclerView(POEMS, recyclerPoems);
    }



    private void loadRecyclerView(String category, RecyclerView recyclerView) {
        Call<BookApiResponse> call = api.getBooksForCategoryName(category);
        call.enqueue(new Callback<BookApiResponse>() {
            @Override
            public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookAPI> books = response.body().getBooks();

                    recyclerView.setLayoutManager(new LinearLayoutManager(BookCategoriesActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    BookAPiAdapter adapter = new BookAPiAdapter(books, BookCategoriesActivity.this);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    Log.d("Show novels", "Error");
                }
            }

            @Override
            public void onFailure(Call<BookApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private List<BookCateItem> getFeaturedItems() {
        List<BookCateItem> list = new ArrayList<>();
        list.add(new BookCateItem("Non fiction",R.drawable.non_fiction_poster));
        list.add(new BookCateItem("History",R.drawable.history_poster));
        list.add(new BookCateItem("Business",R.drawable.poster_business));
        list.add(new BookCateItem("Computer and Programming",R.drawable.poster_computer));
        list.add(new BookCateItem("Science",R.drawable.poster_science));
        return list;
    }

//    private List<Book> getNovelBooks() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book(R.drawable.ic_home_24, "Tiểu Thuyết 1", "Tác giả A"));
//        list.add(new Book(R.drawable.ic_library_24, "Tiểu Thuyết 2", "Tác giả B"));
//        return list;
//    }

//    private List<Book> getChildBooks() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book(R.drawable.ic_store_24, "Truyện Thiếu Nhi", "Tác giả C"));
//        return list;
//    }

//    private List<Book> getNonFicBooks() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book(R.drawable.ic_search_24, "Sách Phi Hư Cấu", "Tác giả D"));
//        return list;
//    }

//    private List<Book> getShortStories() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book(R.drawable.ic_arrow_forward, "Truyện Ngắn 1", "Tác giả E"));
//        return list;
//    }

//    private List<Book> getPoems() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book(R.drawable.ic_home_24, "Thơ Ca", "Tác giả F"));
//        return list;
//    }


}

