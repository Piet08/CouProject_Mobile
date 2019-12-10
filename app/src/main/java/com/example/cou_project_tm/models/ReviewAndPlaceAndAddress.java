package com.example.cou_project_tm.models;

public class ReviewAndPlaceAndAddress {
    private int id;
    private int star;
    private String comment;
    private int isValid;
    private int idUser;
    private PlaceAndAddress place;
    private String date;

    public ReviewAndPlaceAndAddress(){
        this.id = -1;
        this.star = -1;
        this.isValid = -1;
        this.place = new PlaceAndAddress();
        this.idUser = -1;
        this.date = "";
    }

    public ReviewAndPlaceAndAddress(int id, int star, String comment, int isValid, int idUser, PlaceAndAddress place, String date) {
        this.id = id;
        this.star = star;
        this.comment = comment;
        this.isValid = isValid;
        this.idUser = idUser;
        this.place = place;
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

    public PlaceAndAddress getPlace() {
        return place;
    }

    public void setPlace(PlaceAndAddress place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
