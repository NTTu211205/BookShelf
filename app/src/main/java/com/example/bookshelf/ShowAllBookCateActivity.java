package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.example.bookshelf.R;
import com.example.bookshelf.adapters.CategoriesAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShowAllBookCateActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> stringList;
    ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_all_book_cate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        imgButton = findViewById(R.id.btn_back);
        imgButton.setOnClickListener(v ->  {
            finish();
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_bookstore);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(ShowAllBookCateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            }
            else if (itemId == R.id.nav_bookstore){
                Intent intent = new Intent(ShowAllBookCateActivity.this, BookCategoriesActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            else if(itemId == R.id.nav_search){
                Intent intent = new Intent(ShowAllBookCateActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            else if(itemId == R.id.nav_library){
                Intent intent = new Intent(ShowAllBookCateActivity.this, LibraryActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            else return false;
        });

        recyclerView = findViewById(R.id.rv_allBookCate);
        stringList = new ArrayList<>();
        stringList.add("Fiction");
        stringList.add("Non-Fiction");
        stringList.add("Science Fiction");
        stringList.add("Mystery");
        stringList.add("Romance");
        stringList.add("Fantasy");
        stringList.add("Thriller");
        stringList.add("Horror");
        stringList.add("Historical Fiction");
        stringList.add("Young Adult");
        stringList.add("Contemporary");
        stringList.add("Humor");
        stringList.add("Poetry");
        stringList.add("Adventure");
        stringList.add("Science");
        stringList.add("Self-Help");
        stringList.add("Business");
        stringList.add("Travel");
        stringList.add("Religion");
        stringList.add("Philosophy");
        stringList.add("History");
        stringList.add("Comics");

        CategoriesAdapter adapter = new CategoriesAdapter(stringList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}