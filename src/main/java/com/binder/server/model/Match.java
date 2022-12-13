package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user1Id")
    private Long user1Id;

    @Column(name = "book1Id")
    private Long book1Id;

    @Column(name = "thumbnail1", columnDefinition = "TEXT")
    private String thumbnail1;

    @Column(name = "username1")
    private String username1;
    @Column(name = "condition1", columnDefinition = "TEXT")
    private String condition1;
    @Column(name = "title1")
    private String title1;
    @Column(name = "author1")
    private String author1;

    @Column(name = "user2Id")
    private Long user2Id;

    @Column(name = "username2")
    private String username2;
    @Column(name = "condition2", columnDefinition = "TEXT")
    private String condition2;
    @Column(name = "title2")
    private String title2;
    @Column(name = "author2")
    private String author2;
    @Column(name= "book2Id")
    private Long book2Id;

    @Column(name = "thumbnail2", columnDefinition = "TEXT")
    private String thumbnail2;
    public Match() {
    }

    public Match(Long id, Long user1Id, Long book1Id, String thumbnail1, String username1, String condition1, String title1, String author1, Long user2Id, String username2, String condition2, String title2, String author2, Long book2Id, String thumbnail2) {
        this.id = id;
        this.user1Id = user1Id;
        this.book1Id = book1Id;
        this.thumbnail1 = thumbnail1;
        this.username1 = username1;
        this.condition1 = condition1;
        this.title1 = title1;
        this.author1 = author1;
        this.user2Id = user2Id;
        this.username2 = username2;
        this.condition2 = condition2;
        this.title2 = title2;
        this.author2 = author2;
        this.book2Id = book2Id;
        this.thumbnail2 = thumbnail2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public Long getBook1Id() {
        return book1Id;
    }

    public void setBook1Id(Long book1Id) {
        this.book1Id = book1Id;
    }

    public Long getBook2Id() {
        return book2Id;
    }

    public void setBook2Id(Long book2Id) {
        this.book2Id = book2Id;
    }

    public String getThumbnail1() {
        return thumbnail1;
    }

    public void setThumbnail1(String thumbnail1) {
        this.thumbnail1 = thumbnail1;
    }

    public String getThumbnail2() {
        return thumbnail2;
    }

    public void setThumbnail2(String thumbnail2) {
        this.thumbnail2 = thumbnail2;
    }

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(String condition1) {
        this.condition1 = condition1;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(String condition2) {
        this.condition2 = condition2;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }
}
