package com.example.bookshelf.api;

import com.example.bookshelf.api.models.BookAPI;
import com.example.bookshelf.api.response.BookApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("volumes/{id}")
    Call<BookAPI> getBook(@Path("id") String id);

    @GET("volumes")
    Call<BookApiResponse> getBooksForCategoryName(@Query("q") String query);
}
