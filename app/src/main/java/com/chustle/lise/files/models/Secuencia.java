package com.chustle.lise.files.models;

import java.util.List;

public class Secuencia {

    private String nombreSecuencia, artistaSecuencia;

    private long idSecuencia;

    List<PistaSecuencia> listaPistas;

    public List<PistaSecuencia> getListaPistas() {
        return listaPistas;
    }

    public void setListaPistas(List<PistaSecuencia> listaPistas) {
        this.listaPistas = listaPistas;
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
