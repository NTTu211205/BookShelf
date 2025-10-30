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
    private long bookId;
    @ColumnInfo(name="bookApiId")
    private String bookApiId;



    //Constructor
    public BookDB(String bookApiId) {
        this.bookApiId = bookApiId;
    }



    //Getter - Setter
    public String getBookApiId() {
        return bookApiId;
    }

    public void setBookApiId(String bookApiId) {
        this.bookApiId = bookApiId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
