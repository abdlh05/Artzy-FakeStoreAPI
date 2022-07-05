package com.blogspot.codesgram.merkletest.Models;

public class UserModel {
    private String email,username,password;

    public UserModel(String email, String username, String password) {
        //initialized
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}