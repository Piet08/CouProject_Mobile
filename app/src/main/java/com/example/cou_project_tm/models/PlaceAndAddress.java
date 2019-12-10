package com.example.cou_project_tm.models;

import java.util.Objects;

public class PlaceAndAddress {
    private int id;
    private String name;
    private String description;
    private EnumTypePlace type;
    private Address address;

    public PlaceAndAddress(){
        this.id = -1;
        this.name = "";
        this.description ="";
        this.type = EnumTypePlace.ALL;
        this.address = new Address();
    }

    public PlaceAndAddress(int id, String name, String description, EnumTypePlace type, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceAndAddress place = (PlaceAndAddress) o;
        return id == place.id &&
                Objects.equals(name, place.name) &&
                Objects.equals(description, place.description) &&
                type == place.type &&
                Objects.equals(address, place.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, address);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EnumTypePlace getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(EnumTypePlace type) {
        this.type = type;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

