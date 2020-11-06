package com.chustle.lise.files.models;

import java.util.ArrayList;

//This class represents al the data needed to a Sequence
public class Secuencia {

    //List of bookmarks
    private ArrayList<PistaMarcadores> listaPistasMarcadores = new ArrayList<>();

    private String nombreSecuencia, artistaSecuencia;
    private long idSecuencia;

    public ArrayList<PistaMarcadores> getListaPistasMarcadores() {
        return listaPistasMarcadores;
    }

    //This method returns the information from the File asocciated to this Sequence instance, but
    //orders it as the user wanted and saved in the file
    public ArrayList<Pista> getListaPistas() {
        ArrayList<Pista> listaPistas = new ArrayList<>();

        for (Pista p : listaPistasMarcadores)
            listaPistas.add(p);


        for (Pista p: listaPistas){
            if(p.indice != listaPistas.indexOf(p)){
                listaPistas.remove(p);
                listaPistas.add(p.indice, p);
            }
        }

        return listaPistas;
    }

    public String getNombreSecuencia() {
        return nombreSecuencia;
    }

    public void setNombreSecuencia(String nombreSecuencia) {
        this.nombreSecuencia = nombreSecuencia;
    }

    public String getArtistaSecuencia() {
        return artistaSecuencia;
    }

    public void setArtistaSecuencia(String artistaSecuencia) {
        this.artistaSecuencia = artistaSecuencia;
    }

    public long getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(long idSecuencia) {
        this.idSecuencia = idSecuencia;
    }
}
