package com.example.binderserver.model;

import org.springframework.data.annotation.Id;


public class UserTable {

    @Id
    private int id;
    private String username;
    private String city;
    private int postal_code;
    private int phone_number;
    private int reputation;
    private Boolean is_banned;

    public UserTable() {

    }

    public UserTable(String username, String city, int postal_code, int phone_number, int reputation, Boolean is_banned) {
        this.username = username;
        this.city = city;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.reputation = reputation;
        this.is_banned = is_banned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
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





