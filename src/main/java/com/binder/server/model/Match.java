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

    @Column(name = "username1")
    private String username1;

    @Column(name = "user2Id")
    private Long user2Id;

    @Column(name = "username2")
    private String username2;

    @Column(name = "book1Id")
    private Long book1Id;

    @Column(name= "book2Id")
    private Long book2Id;

    public Match() {
    }

    public Match(Long id, Long user1Id, String username1, Long user2Id, String username2, Long book1Id, Long book2Id) {
        this.id = id;
        this.user1Id = user1Id;
        this.username1 = username1;
        this.user2Id = user2Id;
        this.username2 = username2;
        this.book1Id = book1Id;
        this.book2Id = book2Id;
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
}
