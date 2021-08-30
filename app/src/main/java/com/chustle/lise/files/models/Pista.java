package com.chustle.lise.files.models;

//This class represents a Track of a Sequence (Secuencia)
public class Pista {

    String titulo;
    int indice;
    boolean expandido;

    public Pista(int indice, boolean expandido) {
        this.indice = indice;
        this.expandido = expandido;
    }

    public boolean isExpandido() {
        return expandido;
    }
    public void setExpandido(boolean expandido){
        this.expandido = expandido;
    }

    public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo = titulo;}
}
