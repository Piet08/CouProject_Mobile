package com.example.cou_project_tm.models;

import java.util.Objects;

public class UserAndAddress {
    private User user;
    private Address address;

    public UserAndAddress(){
        this.user = new User();
        this.address = new Address();
    }

    public UserAndAddress(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserAndAddress{" +
                "user=" + user +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndAddress that = (UserAndAddress) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, address);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
