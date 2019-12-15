package com.example.cou_project_tm.models;

import java.util.Objects;

public class AuthenticateModel {
    private String username;
    private String password;

    public AuthenticateModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticateModel() {
        this.username = "";
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public String toString() {
        return "AuthenticateUser{" +
                "username =" + username +
                ", password='" + password + '\'';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticateModel that = (AuthenticateModel) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
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
