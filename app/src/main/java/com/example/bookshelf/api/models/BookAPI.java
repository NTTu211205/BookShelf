package com.example.bookshelf.api.models;

import com.google.gson.annotations.SerializedName;

public class BookAPI {
    @SerializedName("id")
    private String id;
    @SerializedName("volumeInfo")
    private BookInfo bookInfo;

    public BookAPI(String id, BookInfo bookInfo) {
        this.id = id;
        this.bookInfo = bookInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
