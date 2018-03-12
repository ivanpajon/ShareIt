package com.shareit.shareitdam.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Usuario {

    public String idUsuario;
    public String name;
    public String lastName;
    public String phone;
    public String username;
    public String email;
    public boolean isAdmin;
    public ArrayList<String> idComunidades;


    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String email) {
        this.idUsuario="";
        this.name = "";
        this.lastName = "";
        this.phone = "";
        this.username = "";
        this.email = email;
        this.isAdmin = false;
        this.idComunidades=new ArrayList<>();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public ArrayList<String> getIdComunidades() {
        return idComunidades;
    }

    public void setIdComunidades(ArrayList<String> idComunidades) {
        this.idComunidades = idComunidades;
    }
}
