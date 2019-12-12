package com.example.cou_project_tm.models;

import java.util.Objects;

public class ReviewAndPlaceAndAddress {
    private Review review;
    private PlaceAndAddress place;

    public ReviewAndPlaceAndAddress(){
        this.review = new Review();
        this.place = new PlaceAndAddress();
    }

    public ReviewAndPlaceAndAddress(Review review, PlaceAndAddress place) {
        this.review = review;
        this.place = place;
    }

    @Override
    public String toString() {
        return "ReviewAndPlaceAndAddress{" +
                "review=" + review +
                ", place=" + place +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewAndPlaceAndAddress that = (ReviewAndPlaceAndAddress) o;
        return Objects.equals(review, that.review) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review, place);
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public PlaceAndAddress getPlace() {
        return place;
    }

    public void setPlace(PlaceAndAddress place) {
        this.place = place;
    }
}
