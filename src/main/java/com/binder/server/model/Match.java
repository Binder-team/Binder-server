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
    @Column(name = "email1")
    private String email1;
    @Column(name = "phone1")
    private String phone1;

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
    @Column(name = "didUser1Accept")
    private Boolean didUser1Accept;
    @Column(name = "didUser1Exchange")
    private Boolean didUser1Exchange;
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
    @Column(name = "email2")
    private String email2;
    @Column(name = "phone2")
    private String phone2;
    @Column(name = "thumbnail2", columnDefinition = "TEXT")
    private String thumbnail2;
    @Column(name = "didUser2Accept")
    private Boolean didUser2Accept;
    @Column(name = "didUser2Exchange")
    private Boolean didUser2Exchange;
    public Match() {
    }

    public Match(Long id, Long user1Id, Long book1Id, String email1, String phone1, String thumbnail1, String username1, String condition1, String title1, String author1, Boolean didUser1Accept, Boolean didUser1Exchange, Long user2Id, String username2, String condition2, String title2, String author2, Long book2Id, String email2, String phone2, String thumbnail2, Boolean didUser2Accept, Boolean didUser2Exchange) {
        this.id = id;
        this.user1Id = user1Id;
        this.book1Id = book1Id;
        this.email1 = email1;
        this.phone1 = phone1;
        this.thumbnail1 = thumbnail1;
        this.username1 = username1;
        this.condition1 = condition1;
        this.title1 = title1;
        this.author1 = author1;
        this.didUser1Accept = didUser1Accept;
        this.didUser1Exchange = didUser1Exchange;
        this.user2Id = user2Id;
        this.username2 = username2;
        this.condition2 = condition2;
        this.title2 = title2;
        this.author2 = author2;
        this.book2Id = book2Id;
        this.email2 = email2;
        this.phone2 = phone2;
        this.thumbnail2 = thumbnail2;
        this.didUser2Accept = didUser2Accept;
        this.didUser2Exchange = didUser2Exchange;
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

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Boolean getDidUser1Accept() {
        return didUser1Accept;
    }

    public void setDidUser1Accept(Boolean didUser1Accept) {
        this.didUser1Accept = didUser1Accept;
    }

    public Boolean getDidUser2Accept() {
        return didUser2Accept;
    }

    public void setDidUser2Accept(Boolean didUser2Accept) {
        this.didUser2Accept = didUser2Accept;
    }

    public Boolean getDidUser1Exchange() {
        return didUser1Exchange;
    }

    public void setDidUser1Exchange(Boolean didUser1Exchange) {
        this.didUser1Exchange = didUser1Exchange;
    }

    public Boolean getDidUser2Exchange() {
        return didUser2Exchange;
    }

    public void setDidUser2Exchange(Boolean didUser2Exchange) {
        this.didUser2Exchange = didUser2Exchange;
    }
}
