package com.example.cou_project_tm.models;

import java.util.Objects;

public class ReviewAndUser {
    private Review review;
    private User user;

    public ReviewAndUser(){
        this.review = new Review();
        this.user = new User();
    }

    public ReviewAndUser(Review review, User user) {
        this.review = review;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReviewAndUser{" +
                "review=" + review +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewAndUser that = (ReviewAndUser) o;
        return Objects.equals(review, that.review) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review, user);
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}