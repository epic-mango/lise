package com.chustle.lise.ui.mis_secuencias;

public class ListModelSecuencias {

    private String nombreSecuencia;
    private String artistaSecuencia;
    private String rutaArchivo;

    public static final String NOMBRE_SECUENCIA = "nombre_secuencia";
    public static final String ARTISTA_SECUENCIA = "artista_secuencia";
    public static final String ID_SECUENCIA = "id_secuencia";

    public ListModelSecuencias(String nombreSecuencia, String artistaSecuencia, String rutaArchivo) {
        this.nombreSecuencia = nombreSecuencia;
        this.artistaSecuencia = artistaSecuencia;
        this.rutaArchivo = rutaArchivo;
    }

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
