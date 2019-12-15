package com.example.cou_project_tm.models;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.Objects;

public class Marker implements ClusterItem{
    private int idAdr;
    private String city;
    private String straat;
    private String num;
    private int postalCode;

    private int idPlace;
    private String name;
    private String description;
    private String type;

    private double avgRate;

    private LatLng latLng;

    public Marker(int idAdr, String city, String straat, String num, int postalCode, int idPlace, String name, String description, String type, double avgRate) {

        this.idAdr = idAdr;
        this.city = city;
        this.straat = straat;
        this.num = num;
        this.postalCode = postalCode;
        this.idPlace = idPlace;
        this.name = name;
        this.description = description;
        this.type = type;
        this.avgRate = avgRate;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + idAdr +
                ", city='" + city + '\'' +
                ", straat='" + straat + '\'' +
                ", num='" + num + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }

    public String infoAddressToString(){
        return city + "," + straat + " N° " + num + ", " + postalCode;
    }

    public String infoPlaceToString(){
        String noRating;
        if(avgRate == 0)
            noRating = "Pas d'avis";
        else
            noRating = String.valueOf(avgRate) + "/10";
        return name + " : " + description + "\n Note : " + noRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marker address = (Marker) o;
        return idAdr == address.idAdr &&
                postalCode == address.postalCode &&
                Objects.equals(city, address.city) &&
                Objects.equals(straat, address.straat) &&
                Objects.equals(num, address.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdr, city, straat, num, postalCode, idPlace, name, description, type, avgRate, latLng);
    }

    public int getId() {
        return idAdr;
    }

    public void setId(int id) {
        this.idAdr = id;
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

    public LatLng getLatLng() { return latLng; }

    public void setLatLng(LatLng latLng) { this.latLng = latLng; }

    public int getIdAdr() { return idAdr; }

    public void setIdAdr(int idAdr) { this.idAdr = idAdr; }

    public int getIdPlace() { return idPlace; }

    public void setIdPlace(int idPlace) { this.idPlace = idPlace; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public double getAvgRate() { return avgRate; }

    public void setAvgRate(double avgRate) { this.avgRate = avgRate; }

    @Override
    public LatLng getPosition() { return latLng; }

    @Override
    public String getTitle() { return infoAddressToString(); }

    @Override
    public String getSnippet() { return infoPlaceToString(); }

}
