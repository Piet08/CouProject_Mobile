package com.example.cou_project_tm.models;

import java.util.Objects;

public class Address {
    private int id;
    private String city;
    private String straat;
    private String num;
    private int postalCode;

    public Address(int id, String city, String straat, String num, int postalCode) {
        this.id = id;
        this.city = city;
        this.straat = straat;
        this.num = num;
        this.postalCode = postalCode;
    }

    public Address() {
        this.id = -1;
        this.city = "";
        this.straat = "";
        this.num = "";
        this.postalCode = 0;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", straat='" + straat + '\'' +
                ", num='" + num + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                postalCode == address.postalCode &&
                Objects.equals(city, address.city) &&
                Objects.equals(straat, address.straat) &&
                Objects.equals(num, address.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, straat, num, postalCode);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
