package com.example.bookshelf.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.intellij.lang.annotations.Pattern;

@Entity(tableName = "ImageLink")
public class ImageLinkDB {
    @PrimaryKey(autoGenerate = true)
    private long imageLinkId;
    @ColumnInfo(name="bookInfoId")
    private String bookInfoId;
    @ColumnInfo(name="smallThumbnail")
    private String smallThumnail;
    @ColumnInfo(name="thumbnail")
    private String thumbnail;
    @ColumnInfo(name="small")
    private String small;
    @ColumnInfo(name="medium")
    private String medium;
    @ColumnInfo(name="large")
    private String large;
    @ColumnInfo(name="extraLarge")
    private String extraLarge;



    // Constructor
    public ImageLinkDB(String bookInfoId, String smallThumbnail, String thumbnail, String small, String medium, String large, String extraLarge) {
        this.bookInfoId = bookInfoId;
        this.smallThumnail = smallThumbnail;
        this.thumbnail = thumbnail;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.extraLarge = extraLarge;
    }



    // Getter - Setter
    public long getImageLinkId() {
        return imageLinkId;
    }

    public void setImageLinkId(long imageLinkId) {
        this.imageLinkId = imageLinkId;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public String getSmallThumnail() {
        return smallThumnail;
    }

    public void setSmallThumnail(String smallThumnail) {
        this.smallThumnail = smallThumnail;
    }

    public String getThumnail() {
        return thumbnail;
    }

    public void setThumnail(String thumnail) {
        this.thumbnail = thumnail;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }
}
