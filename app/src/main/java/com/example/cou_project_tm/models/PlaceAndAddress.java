package com.example.cou_project_tm.models;

import java.io.Serializable;
import java.util.Objects;

public class PlaceAndAddress implements Serializable {
    private Address address;
    private Place place;
    private double avgRate;

    public PlaceAndAddress(){
        this.address = new Address();
        this.place = new Place();
        this.avgRate = 0;
    }

    public PlaceAndAddress(Address address, Place place, double avgRate) {
        this.address = address;
        this.place = place;
        this.avgRate = avgRate;
    }

    @Override
    public String toString() {
        return "PlaceAndAddress{" +
                "address=" + address +
                ", place=" + place +
                ", avgRate=" + avgRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceAndAddress that = (PlaceAndAddress) o;
        return Double.compare(that.avgRate, avgRate) == 0 &&
                Objects.equals(address, that.address) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, place, avgRate);
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}

