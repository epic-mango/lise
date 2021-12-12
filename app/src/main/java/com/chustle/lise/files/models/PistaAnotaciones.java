package com.chustle.lise.files.models;

import java.util.ArrayList;
import java.util.List;

//This class extends a Track of type Bookmark
public class PistaAnotaciones extends PistaMarcadores {
    public PistaAnotaciones(int indice, boolean expandido, String titulo, ArrayList<Marcador> listaMarcadores) {
        super(indice, expandido, titulo, listaMarcadores);
    }
}
