package com.consolemonkey.model;

public class User {

    private String id;
    private String password;

    public User(String id) {
        this.id = id;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
