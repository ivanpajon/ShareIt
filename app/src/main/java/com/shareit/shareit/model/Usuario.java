package com.shareit.shareit.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuario {
    public String name;
    public String lastName;
    public String phone;
    public String username;
    public String email;
    public boolean isAdmin;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String email) {
        this.name = "";
        this.lastName = "";
        this.phone = "";
        this.username = "";
        this.email = email;
        this.isAdmin = false;
    }
}
