package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dummyauth")
public class DummyAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "username")
    private String username;
    @Column(name = "token")
    private String token;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public DummyAuth() {
    }

    public DummyAuth(Long user_id, String username, String token) {
        this.user_id = user_id;
        this.username = username;
        this.token = token;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
