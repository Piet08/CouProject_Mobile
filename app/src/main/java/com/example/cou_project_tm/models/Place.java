package com.example.cou_project_tm.models;

import java.util.Objects;

public class Place {
    private int id;
    private String name;
    private String description;
    private EnumTypePlace type;
    private int idAdr;

    public Place(){
        this.id = -1;
        this.name = "";
        this.description ="";
        this.type = EnumTypePlace.ALL;
        this.idAdr = -1;
    }

    public Place(int id, String name, String description, EnumTypePlace type, int idAdr) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.idAdr = idAdr;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", idAdr=" + idAdr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id &&
                Objects.equals(name, place.name) &&
                Objects.equals(description, place.description) &&
                type == place.type &&
                Objects.equals(idAdr, place.idAdr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, idAdr);
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

    public int getIdAdr() {
        return idAdr;
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

    public void setIdAdr(int idAdr) {
        this.idAdr = idAdr;
    }
}

