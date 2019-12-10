package com.example.cou_project_tm.models;

public class Review {
    private int id;
    private int star;
    private String comment;
    private int isValid;
    private int idUser;
    private int idPlace;
    private String date;

    public Review(){
        this.id = -1;
        this.star = -1;
        this.isValid = -1;
        this.idPlace = -1;
        this.idUser = -1;
        this.idPlace = -1;
        this.date = "";
    }

    public Review(int id, int star, String comment, int isValid, int idUser, int idPlace, String date) {
        this.id = id;
        this.star = star;
        this.comment = comment;
        this.isValid = isValid;
        this.idUser = idUser;
        this.idPlace = idPlace;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
