package com.example.cou_project_tm.models;

public enum EnumTypePlace {
    ALL ("ALL"),
    RESTAURANT ("Restaurant"),
    FAST_FOOD ("Fast-Food"),
    MUSEE ("Musée"),
    MAGASIN ("Magasin"),
    BAR ("Bar"),
    BRASSERIE ("Brasserie"),
    DIVERTISSEMENT ("Divertissement");

    private String name;

    EnumTypePlace(String n){
        this.name = n;
    }

    public String toString(){
        return  name;
    }
}
