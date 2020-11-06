package com.chustle.lise.files.models;

import java.util.List;

public class PistaMarcadores extends Pista {

    String titulo;

    List<Marcador> listaMarcadores;

    public PistaMarcadores(int indice, boolean expandido, String titulo, List<Marcador> listaMarcadores) {
        super(indice, expandido);
        this.titulo = titulo;
        this.listaMarcadores = listaMarcadores;
    }

    public String getTitulo() {
        return titulo;
    }
}
