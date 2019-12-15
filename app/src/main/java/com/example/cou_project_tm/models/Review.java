package com.example.cou_project_tm.models;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", star=" + star +
                ", comment='" + comment + '\'' +
                ", isValid=" + isValid +
                ", idUser=" + idUser +
                ", idPlace=" + idPlace +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                star == review.star &&
                isValid == review.isValid &&
                idUser == review.idUser &&
                idPlace == review.idPlace &&
                Objects.equals(comment, review.comment) &&
                Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, star, comment, isValid, idUser, idPlace, date);
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
