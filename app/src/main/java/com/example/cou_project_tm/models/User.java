package com.example.cou_project_tm.models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String surname;
    private String pseudo;
    private String email;
    private String hashpwd;
    private String type;
    private int idAdr;
    private String token;

    public User(){
        this.id = -1;
        this.name = "Guest";
        this.surname = "";
        this.pseudo = "Guest";
        this.email = "";
        this.hashpwd = "";
        this.type = "";
        this.token = "";
        this.type = "-1";
        this.idAdr = -1;
    }

    public User(int id, String name, String surname, String pseudo, String email, String hashpwd, int idAdr, String token,String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pseudo = pseudo;
        this.email = email;
        this.hashpwd = hashpwd;
        this.type = type;
        this.idAdr = idAdr;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", hashpwd='" + hashpwd + '\'' +
                ", type='" + type + '\'' +
                ", idAdr=" + idAdr +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                idAdr == user.idAdr &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(pseudo, user.pseudo) &&
                Objects.equals(email, user.email) &&
                Objects.equals(hashpwd, user.hashpwd) &&
                Objects.equals(type, user.type) &&
                Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pseudo, email, hashpwd, type, idAdr, token);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashpwd() {
        return hashpwd;
    }

    public void setHashpwd(String hashpwd) {
        this.hashpwd = hashpwd;
    }

    public int getIdAdr() {
        return idAdr;
    }

    public void setIdAdr(int idAdr) {
        this.idAdr = idAdr;
    }

    public String getToken() {
        if(token == null)token = " ";
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
