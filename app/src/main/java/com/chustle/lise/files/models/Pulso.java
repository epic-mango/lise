package com.chustle.lise.files.models;

import java.util.ArrayList;

public class Pulso {
    int inicio;
    int cantidadFiguras;
    int figura;
    int bmp;
    ArrayList<Beat> patron;

    public Pulso(int inicio, int cantidadFiguras, int figura, int bmp, ArrayList<Beat> patron) {

        this.inicio = inicio;
        this.cantidadFiguras = cantidadFiguras;
        this.figura = figura;
        this.bmp = bmp;
        this.patron = patron;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getCantidadFiguras() {
        return cantidadFiguras;
    }

    public void setCantidadFiguras(int cantidadFiguras) {
        this.cantidadFiguras = cantidadFiguras;
    }

    public int getFigura() {
        return figura;
    }

    public void setFigura(int figura) {
        this.figura = figura;
    }

    public int getBmp() {
        return bmp;
    }

    public void setBmp(int bmp) {
        this.bmp = bmp;
    }

    public ArrayList<Beat> getPatron() {
        return patron;
    }

    public void setPatron(ArrayList<Beat> patron) {
        this.patron = patron;
    }

    public String getPatronString() {
        StringBuffer buffer = new StringBuffer();
        for (Beat b : patron)buffer.append(b.texto);
        return buffer.toString();
    }
}
