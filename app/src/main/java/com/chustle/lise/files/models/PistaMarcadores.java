package com.chustle.lise.files.models;

import java.util.ArrayList;
import java.util.List;

//This class extends a Track of type Bookmark
public class PistaMarcadores extends Pista {


    ArrayList<Marcador> listaMarcadores;

    public PistaMarcadores(int indice, boolean expandido, String titulo,
                           ArrayList<Marcador> listaMarcadores) {
        super(indice, expandido);
        this.titulo = titulo;
        this.listaMarcadores = listaMarcadores;
    }


    public ArrayList<Marcador> getListaMarcadores() {
        return listaMarcadores;
    }
}
