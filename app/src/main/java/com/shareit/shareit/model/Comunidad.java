package com.shareit.shareit.model;

import java.util.ArrayList;

public class Comunidad {

    private String idComunidad;
    private String nombreComunidad;
    private String descripcionComunidad;
    private String fotoComunidad;
    private ArrayList<String> idUsuarios;

    public Comunidad(String nombreComunidad, String descripcionComunidad, String fotoComunidad) {

        this.nombreComunidad = nombreComunidad;
        this.descripcionComunidad = descripcionComunidad;
        this.fotoComunidad = fotoComunidad;
        this.idUsuarios = new ArrayList<>();
    }

    public String getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(String idComunidad) {
        this.idComunidad = idComunidad;
    }

    public String getNombreComunidad() {
        return nombreComunidad;
    }

    public void setNombreComunidad(String nombreComunidad) {
        this.nombreComunidad = nombreComunidad;
    }

    public String getDescripcionComunidad() {
        return descripcionComunidad;
    }

    public void setDescripcionComunidad(String descripcionComunidad) {
        this.descripcionComunidad = descripcionComunidad;
    }

    public String getFotoComunidad() {
        return fotoComunidad;
    }

    public void setFotoComunidad(String fotoComunidad) {
        this.fotoComunidad = fotoComunidad;
    }

    public ArrayList<String> getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(ArrayList<String> idUsuarios) {
        this.idUsuarios = idUsuarios;
    }


}
