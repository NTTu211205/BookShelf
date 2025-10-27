package com.example.bookshelf.database.models;

import androidx.room.Relation;

import com.example.bookshelf.api.models.BookInfo;

public class BookInfoWithImage {
    private BookInfo bookInfo;

    @Relation(
            parentColumn = "bookInfoId",
            entityColumn = "bookInfoId"
    )
    private ImageLinkDB imageLinkDB;
}
