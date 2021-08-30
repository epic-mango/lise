package com.chustle.lise.files.models;

import java.util.ArrayList;

//This class extends a Track of type Bookmark
public class PistaPulsos extends Pista {


    ArrayList<Pulso> listaPulsos;

    public PistaPulsos(int indice, boolean expandido, String titulo,
                       ArrayList<Pulso> listaPulsos) {
        super(indice, expandido);
        this.titulo = titulo;
        this.listaPulsos = listaPulsos;
    }


    public ArrayList<Pulso> getListaPulsos() {
        return listaPulsos;
    }
}
