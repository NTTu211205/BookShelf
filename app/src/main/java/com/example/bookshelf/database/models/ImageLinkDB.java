package com.example.bookshelf.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.intellij.lang.annotations.Pattern;

@Entity
public class ImageLinkDB {
    @PrimaryKey
    private String imageLinkId;
    @ColumnInfo(name="bookInfoId")
    private String bookInfoId;
    @ColumnInfo(name="smallThumbnail")
    private String smallThumnail;
    @ColumnInfo(name="thumbnail")
    private String thumnail;
    @ColumnInfo(name="small")
    private String small;
    @ColumnInfo(name="medium")
    private String medium;
    @ColumnInfo(name="large")
    private String large;
    @ColumnInfo(name="extraLarge")
    private String extraLarge;
}
