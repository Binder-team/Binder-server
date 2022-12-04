package com.example.binderserver.resource;

public class UserTableRequest {
    private String username;
    private String city;
    private String postal_code;
    private String phone_number;
    private int reputation;
    private Boolean is_banned;

    public UserTableRequest() {
    }

    public UserTableRequest(String username, String city, String postal_code, String phone_number, int reputation, Boolean is_banned) {
        this.username = username;
        this.city = city;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.reputation = reputation;
        this.is_banned = is_banned;
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
