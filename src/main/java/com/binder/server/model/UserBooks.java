package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_books")
public class UserBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "book_id")
    private String book_id;

    @Column(name = "is_available")
    private boolean is_available;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "condition")
    private int condition;

    @Column(name = "image_url", columnDefinition="TEXT")
    private String image_url;

    @Column(name = "thumbnail_url", columnDefinition="TEXT")
    private String thumbnail_url;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    public UserBooks() {
    }

    public UserBooks(int user_id, String book_id, boolean is_available, String isbn, int condition, String image_url, String thumbnail_url, String title, String author) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.is_available = is_available;
        this.isbn = isbn;
        this.condition = condition;
        this.image_url = image_url;
        this.thumbnail_url = thumbnail_url;
        this.title = title;
        this.author = author;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
