package org.example.ejercicioscookies.Entidades;

import java.io.Serializable;

public class Persona implements Serializable {
    private String  name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
