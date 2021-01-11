package com.example.demo.Helpers;

import java.io.Serializable;

public class RegisterHelper implements Serializable {

    private String name;
    private String email;
    private String degree;
    private String username;
    private String password;

    public RegisterHelper(String name, String degree, String email, String password, String username) {

        this.name=name;
       this.email=email;
       this.degree = degree;
       this.username = username;
       this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
