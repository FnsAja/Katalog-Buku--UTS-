package com.example.katalogbuku;

public class Buku {
    public String id, bookName, author, year, desc;
    public Integer image;

    public Buku(String id, String bookName, String author, String year, String desc, Integer image){
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.desc = desc;
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
