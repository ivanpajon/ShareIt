package com.shareit.shareit.model;

import android.widget.ImageView;

public class Oferta {

    private String nombreOferta;
    private String descripcionOferta;
    private int imageOferta;

    public Oferta(){

    }
    public Oferta(String nombreOFertas, String descripcionOfertas, int imageOferta) {
        this.nombreOferta = nombreOFertas;
        this.descripcionOferta = descripcionOfertas;
        this.imageOferta = imageOferta;
    }

    public int getImageOferta() {
        return imageOferta;
    }

    public void setImageOferta(int imageOferta) {
        this.imageOferta = imageOferta;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOFertas) {
        this.nombreOferta = nombreOFertas;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOfertas) {
        this.descripcionOferta = descripcionOfertas;
    }
}
