package com.chustle.lise.files.models;

import java.util.ArrayList;

//This class represents al the data needed to a Sequence
public class Secuencia {

    //Usada por el fragmentSecuencia para notificar que se han hecho cambios
    public interface SecuenciaChangedListener {
        void onChange();
    }

    private String nombreSecuencia, artistaSecuencia;
    private long idSecuencia, versionSecuencia;

    //List of bookmarks
    private ArrayList<PistaMarcadores> listaPistasMarcadores;

    //List of Tempos
    private ArrayList<Tempo> listaTempos;

    public ArrayList<PistaMarcadores> getListaPistasMarcadores() {
        return listaPistasMarcadores;
    }

    public Secuencia(String nombreSecuencia, String artistaSecuencia, long idSecuencia, long versionSecuencia, ArrayList<PistaMarcadores> listaPistasMarcadores, int tempoInicial) {
        this.nombreSecuencia = nombreSecuencia;
        this.artistaSecuencia = artistaSecuencia;
        this.idSecuencia = idSecuencia;
        this.versionSecuencia = versionSecuencia;
        this.listaPistasMarcadores = listaPistasMarcadores;
        this.listaTempos = new ArrayList<>();
        this.listaTempos.add(new Tempo(1, tempoInicial, 4,4));
    }

    public void setListaPistasMarcadores(ArrayList<PistaMarcadores> listaPistasMarcadores) {
        this.listaPistasMarcadores = listaPistasMarcadores;
    }

    public long getVersionSecuencia() {
        return versionSecuencia;
    }

    public void setVersionSecuencia(long versionSecuencia) {
        this.versionSecuencia = versionSecuencia;
    }


    //This method returns the information from the File associated to this Sequence instance, but
    //orders it as the user wanted and saved in the file
    public ArrayList<Pista> getListaPistas() {
        ArrayList<Pista> listaPistas = new ArrayList<>();

        for (Pista p : listaPistasMarcadores)
            listaPistas.add(p);


        for (Pista p : listaPistas) {
            if (p.indice != listaPistas.indexOf(p)) {
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
