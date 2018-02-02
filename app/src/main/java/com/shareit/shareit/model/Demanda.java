package com.shareit.shareit.model;

import android.widget.ImageView;

public class Demanda {

    private String nombreDemandas;
    private String descripcionDemandas;
    private ImageView image;

    public Demanda(String nombreDemandas, String descripcionDemandas, ImageView image) {
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

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
