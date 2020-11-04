package com.chustle.lise.files.models;

public class PistaSecuencia {
    int compasInicio;
    int indice;
    boolean expandido;

    public PistaSecuencia(int compasInicio, int indice, boolean expandido) {
        this.compasInicio = compasInicio;
        this.indice = indice;
        this.expandido = expandido;
    }

    public boolean isExpandido() {
        return expandido;
    }
}
