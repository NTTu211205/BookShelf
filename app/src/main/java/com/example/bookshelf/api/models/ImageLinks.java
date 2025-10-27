package com.example.bookshelf.api.models;

import com.google.gson.annotations.SerializedName;

public class ImageLinks {
    @SerializedName("smallThumbnail")
    private String smallThumnail;
    @SerializedName("thumbnail")
    private String thumnail;
    @SerializedName("small")
    private String small;
    @SerializedName("medium")
    private String medium;
    @SerializedName("large")
    private String large;
    @SerializedName("extraLarge")
    private String extraLarge;

    public ImageLinks(String smallThumnail, String thumnail, String small, String medium, String large, String extraLarge) {
        this.smallThumnail = smallThumnail;
        this.thumnail = thumnail;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.extraLarge = extraLarge;
    }

    public String getSmallThumnail() {
        return smallThumnail;
    }

    public void setSmallThumnail(String smallThumnail) {
        this.smallThumnail = smallThumnail;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
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
