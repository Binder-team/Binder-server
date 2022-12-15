package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_books")
public class UserBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "book_id")
    private String book_id;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "condition", columnDefinition = "TEXT")
    private String condition;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String image_url;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnail_url;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    public UserBooks() {
    }

    public UserBooks(long id, Long userId, String book_id, boolean isAvailable, String isbn, String condition, String description, String image_url, String thumbnail_url, String title, String author) {
        this.id = id;
        this.userId = userId;
        this.book_id = book_id;
        this.isAvailable = isAvailable;
        this.isbn = isbn;
        this.condition = condition;
        this.description = description;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIs_available(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

