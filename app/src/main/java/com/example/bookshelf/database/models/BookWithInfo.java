package com.example.bookshelf.database.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.bookshelf.api.models.BookInfo;

public class BookWithInfo {
    @Embedded
    private BookDB book;

    @Relation(
        parentColumn = "bookId",
        entityColumn = "bookId")
    private BookInfo bookInfo;
}
