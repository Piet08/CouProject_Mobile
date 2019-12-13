package com.example.cou_project_tm.models;

import java.util.Objects;

public class AuthenticateModel {
    private String username;
    private String password;
    private boolean remember;

    public AuthenticateModel(String username, String password, boolean remember) {
        this.username = username;
        this.password = password;
        this.remember = remember;
    }

    public AuthenticateModel() {
        this.username = "";
        this.password = "";
        this.remember = false;
    }

    public String getUsername() {
        return username;
    }

    public String toString() {
        return "AuthenticateUser{" +
                "username =" + username +
                ", password='" + password + '\'' +
                ", remembere=" + remember+ '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticateModel that = (AuthenticateModel) o;
        return remember == that.remember &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, remember);
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
