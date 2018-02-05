package com.shareit.shareit.model;

import android.widget.ImageView;

public class Demanda {

    private String nombreDemandas;
    private String descripcionDemandas;
    private int image;

    public Demanda(String nombreDemandas, String descripcionDemandas, int image) {
        this.nombreDemandas = nombreDemandas;
        this.descripcionDemandas = descripcionDemandas;
        this.image = image;
    }

    public String getNombreDemandas() {
        return nombreDemandas;
    }

    public void setNombreDemandas(String nombreDemandas) {
        this.nombreDemandas = nombreDemandas;
    }

    public String getDescripcionDemandas() {
        return descripcionDemandas;
    }

    public void setDescripcionDemandas(String descripcionDemandas) {
        this.descripcionDemandas = descripcionDemandas;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
