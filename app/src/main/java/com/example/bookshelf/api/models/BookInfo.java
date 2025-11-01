package com.example.bookshelf.api.models;

import com.google.gson.annotations.SerializedName;

public class BookInfo {
    @SerializedName("title")
    private String title;
    @SerializedName("authors")
    private String[] authors;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("publishedDate")
    private String publishedDate;
    @SerializedName("description")
    private String description;
    @SerializedName("pageCount")
    private int numPage;
    @SerializedName("printType")
    private String printType;
    @SerializedName("categories")
    private String[] catrgories;
    @SerializedName("language")
    private String language;
    @SerializedName("previewLink")
    private String previewLink;
    @SerializedName("imageLinks")
    private ImageLinks imageLinks;

    public BookInfo(String title, String[] authors, String publisher, String publishedDate, String description, int numPage, String printType, String[] catrgories, String language, String previewLink, ImageLinks imageLinks) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.numPage = numPage;
        this.printType = printType;
        this.catrgories = catrgories;
        this.language = language;
        this.previewLink = previewLink;
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        if (authors == null) {
            return "";
        }

        String stringAuthors = "";
        for (String i: authors) {
            stringAuthors += i + ", ";
        }
        return stringAuthors.substring(0, stringAuthors.length() - 2);
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getCatrgories() {
        String stringCategories = "";
        for (String i:catrgories) {
            stringCategories += i + ",\n" ;
        }
        return stringCategories.substring(0, stringCategories.length() - 2);
    }

    public void setCatrgories(String[] catrgories) {
        this.catrgories = catrgories;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
