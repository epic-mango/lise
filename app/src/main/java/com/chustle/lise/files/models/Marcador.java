package com.chustle.lise.files.models;

//This class will save the bookmark information of a Bookmarks Track (PistaMarcadores)

public class Marcador {
    int inicio;
    String nombre;
    boolean tiempo;

    public Marcador(int inicio, String nombre, boolean tiempo) {
        this.inicio = inicio;
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public int getInicio() {
        return inicio;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isTiempo() {
        return tiempo;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIsTiempo(boolean tiempo) {
        this.tiempo = tiempo;
    }
}
