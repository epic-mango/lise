package com.chustle.lise.ui.entrada_datos;

//This object contains the Field name (campo), Default Data(dato) and the Hint(ejemplo) of a Dialog
//Fragment of Strings
public class EntradaDato {
    String campo;
    String dato;
    String ejemplo;

    public EntradaDato(String campo, String dato, String ejemplo) {
        this.campo = campo;
        this.dato = dato;
        this.ejemplo = ejemplo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }
}