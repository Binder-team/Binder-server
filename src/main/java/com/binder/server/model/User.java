package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Index(columnList = "username")
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "city")
    private String city;
    @Column(name = "postal_code")
    private String postal_code;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "reputation")
    private int reputation;
    @Column(name = "is_banned")
    private Boolean is_banned;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_trade_id") //in case you want to custom name
//    private TradeTable tradeTable;

    public User() {
    }

    public User(String username, String city, String postal_code, String phone_number, int reputation, Boolean is_banned) {
        this.username = username;
        this.city = city;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.reputation = reputation;
        this.is_banned = is_banned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public Boolean getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(Boolean is_banned) {
        this.is_banned = is_banned;
    }
}
