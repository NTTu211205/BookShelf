package com.example.bookshelf.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.bookshelf.api.models.ImageLinks;

@Entity
public class BookInfoDB {
    @PrimaryKey
    private String bookInfoId;
    @ColumnInfo(index = true)
    private String bookId;
    @ColumnInfo(name="title")
    private String title;
    @ColumnInfo(name="authors")
    private String[] authors;
    @ColumnInfo(name="publisher")
    private String publisher;
    @ColumnInfo(name="publishedDate")
    private String publishedDate;
    @ColumnInfo(name="description")
    private String description;
    @ColumnInfo(name="pageCount")
    private int numPage;
    @ColumnInfo(name="printType")
    private String printType;
    @ColumnInfo(name="categories")
    private String[] catrgories;
    @ColumnInfo(name="language")
    private String language;
    @ColumnInfo(name="previewLink")
    private String previewLink;
}
