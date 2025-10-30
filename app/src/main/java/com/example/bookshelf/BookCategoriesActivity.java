package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View; // Thêm import
import android.widget.TextView; // Thêm import

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.adapters.BookAPiAdapter;
import com.example.bookshelf.adapters.BookCateAdapter; // Import adapter
import com.example.bookshelf.api.ApiClient;
import com.example.bookshelf.api.ApiService;
import com.example.bookshelf.api.models.BookAPI;
import com.example.bookshelf.api.response.BookApiResponse;
import com.example.bookshelf.models.BookCateItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class BookCategoriesActivity extends AppCompatActivity implements BookCateAdapter.OnCategoryClickListener {
public class BookCategoriesActivity extends AppCompatActivity {

    private final String NOVELS = "subject:novels";
    private final String CHILD_AND_TEENAGER = "subject:child";
    private final String NON_FICTION = "subject:nonfiction";
    private final String SHORT_STORIES = "subject:shortstories";
    private final String POEMS = "subject:poems";

    private final ApiService api = ApiClient.getClient().create(ApiService.class);




    private RecyclerView recyclerFeatured;
    private RecyclerView recyclerNovel, recyclerChild, recyclerNonFic, recyclerShortStories, recyclerPoems;
    private TextView tvNovel, tvChild, tvNonFic, tvShortStories, tvPoems;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_bookstore);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(BookCategoriesActivity.this, MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_library) {
                startActivity(new Intent(BookCategoriesActivity.this, LibraryActivity.class));
                finish();
                return true;
            } else if(itemId == R.id.nav_search) {
                startActivity(new Intent(BookCategoriesActivity.this, SearchActivity.class));
                finish();
                return true;
            }
            return itemId == R.id.nav_bookstore;
        });

        tvNovel = findViewById(R.id.tvNovelAndLiterature);
        tvChild = findViewById(R.id.tvChild);
        tvNonFic = findViewById(R.id.tv_nonFic);
        tvShortStories = findViewById(R.id.tv_ShortStory);
        tvPoems = findViewById(R.id.tv_poems);

//        tvNovel.setOnClickListener(v -> openCategoryList("Tiểu thuyết", NOVELS));
//        tvChild.setOnClickListener(v -> openCategoryList("Thiếu nhi", CHILD_AND_TEENAGER));
//        tvNonFic.setOnClickListener(v -> openCategoryList("Phi hư cấu", NON_FICTION));
//        tvShortStories.setOnClickListener(v -> openCategoryList("Truyện ngắn", SHORT_STORIES));
//        tvPoems.setOnClickListener(v -> openCategoryList("Thơ", POEMS));

        recyclerFeatured = findViewById(R.id.recyclerView_featured);
        recyclerFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BookCateAdapter featuredAdapter = new BookCateAdapter(this, getFeaturedItems());
        recyclerFeatured.setAdapter(featuredAdapter);

        recyclerNovel = findViewById(R.id.rcNovelAndLiterature);
        recyclerChild = findViewById(R.id.recyclerView_child);
        recyclerNonFic = findViewById(R.id.recycler_nonFic);
        recyclerShortStories = findViewById(R.id.recycler_shortStories);
        recyclerPoems = findViewById(R.id.recycler_poems);

        loadRecyclerView(NOVELS, recyclerNovel);
        loadRecyclerView(CHILD_AND_TEENAGER, recyclerChild);
        loadRecyclerView(NON_FICTION, recyclerNonFic);
        loadRecyclerView(SHORT_STORIES, recyclerShortStories);
        loadRecyclerView(POEMS, recyclerPoems);
    }

//    private void openCategoryList(String categoryName, String categoryQuery) {
//        Intent intent = new Intent(this, ShowListBookCategories.class);
//        intent.putExtra(ShowListBookCategories.EXTRA_CATEGORY_NAME, categoryName);
//        intent.putExtra(ShowListBookCategories.EXTRA_CATEGORY_QUERY, categoryQuery);
//        startActivity(intent);
//    }

//    @Override
//    public void onCategoryClick(BookCateItem item) {
//        String categoryQuery;
//
//        String categoryName = item.getType();
//
//        switch (categoryName) {
//            case "Non fiction":
//                categoryQuery = NON_FICTION;
//                break;
//            case "History":
//                categoryQuery = "subject:history";
//                break;
//            case "Business":
//                categoryQuery = "subject:business";
//                break;
//            case "Computer and Programming":
//                categoryQuery = "subject:computers";
//                break;
//            case "Science":
//                categoryQuery = "subject:science";
//                break;
//            default:
//                categoryQuery = "subject:" + categoryName.toLowerCase().replace(" ", "+");
//        }
//
////        openCategoryList(categoryName, categoryQuery);
//    }

    private List<BookCateItem> getFeaturedItems() {
        List<BookCateItem> list = new ArrayList<>();
        list.add(new BookCateItem("Non fiction",R.drawable.non_fiction_poster));
        list.add(new BookCateItem("History",R.drawable.history_poster));
        list.add(new BookCateItem("Business",R.drawable.poster_business));
        list.add(new BookCateItem("Computer and Programming",R.drawable.poster_computer));
        list.add(new BookCateItem("Science",R.drawable.poster_science));
        return list;
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
                } else {
                    Log.d("Show novels", "Error");
                }
            }

            @Override
            public void onFailure(Call<BookApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}