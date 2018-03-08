package com.shareit.shareit.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Demanda {

    private String nombreDemanda;
    private String descripcionDemanda;
    private int image;

    public Demanda() {}

    public Demanda(String nombreDemanda, String descripcionDemanda, int image) {
        this.nombreDemanda = nombreDemanda;
        this.descripcionDemanda = descripcionDemanda;
        this.image = image;
    }

    public String getNombreDemanda() {
        return nombreDemanda;
    }

    public void setNombreDemanda(String nombreDemanda) {
        this.nombreDemanda = nombreDemanda;
    }

    public String getDescripcionDemanda() {
        return descripcionDemanda;
    }

    public void setDescripcionDemanda(String descripcionDemanda) {
        this.descripcionDemanda = descripcionDemanda;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
