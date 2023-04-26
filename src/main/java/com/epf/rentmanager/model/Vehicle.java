package com.epf.rentmanager.model;

public class Vehicle {
    int id;
    String constructeur;
    int nb_places;

    public Vehicle(int id, String constructeur, int nb_places) {
        this.id = id;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }

    public Vehicle() {
        this.id = id;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getConstructeur() {
        return constructeur;
    }


    public int getNb_places() {
        return nb_places;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }


    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }
}
