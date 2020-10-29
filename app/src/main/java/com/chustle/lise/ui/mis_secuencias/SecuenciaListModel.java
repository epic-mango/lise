package com.chustle.lise.ui.mis_secuencias;

public class SecuenciaListModel {

    private String nombreSecuencia;
    private String artistaSecuencia;
    private String rutaArchivo;

    public String getNombreSecuencia() {
        return nombreSecuencia;
    }

    public String getArtistaSecuencia() {
        return artistaSecuencia;
    }

    public void setNombreSecuencia(String nombreSecuencia) {
        this.nombreSecuencia = nombreSecuencia;
    }

    public void setArtistaSecuencia(String artistaSecuencia) {
        this.artistaSecuencia = artistaSecuencia;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
}
