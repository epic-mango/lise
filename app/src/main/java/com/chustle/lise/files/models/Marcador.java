package com.chustle.lise.files.models;

public class Marcador extends PistaSecuencia{
    String nombreMarcador;

    public Marcador(int compasInicio, int indice, boolean expandido, String nombreMarcador) {
        super(compasInicio, indice, expandido);
        this.nombreMarcador = nombreMarcador;
    }
}
