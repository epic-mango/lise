package com.chustle.lise.files.models;

public class Tempo {
    int inicio;
    boolean tiempo;
    int bpm;
    int cantidadFiguras;
    int figura;

    public Tempo(int inicio, boolean tiempo, int bpm, int cantidadFiguras, int figura) {
        this.inicio = inicio;
        this.tiempo = tiempo;
        this.bpm = bpm;
        this.cantidadFiguras = cantidadFiguras;
        this.figura = figura;
    }

}
