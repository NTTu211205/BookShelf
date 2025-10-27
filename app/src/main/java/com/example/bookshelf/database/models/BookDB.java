package com.example.bookshelf.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.bookshelf.api.models.BookInfo;
import com.example.bookshelf.api.models.ImageLinks;
import com.google.gson.annotations.SerializedName;

@Entity
public class BookDB {
    @PrimaryKey(autoGenerate = true)
    private String bookId;

}
