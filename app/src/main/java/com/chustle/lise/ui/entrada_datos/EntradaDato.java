package com.chustle.lise.ui.entrada_datos;

//This object contains the Field name (campo), Default Data(dato) and the Hint(ejemplo) of a Dialog
//Fragment of Strings
public class EntradaDato {

    public static final int TIPO_STRING = 0;
    public static final int TIPO_NUMERO = 1;

    String campo;
    String dato;
    String ejemplo;
    int tipo;

    public EntradaDato(String campo, String dato, String ejemplo, int tipo) {
        this.campo = campo;
        this.dato = dato;
        this.ejemplo = ejemplo;
        this.tipo = tipo;
    }



    public String getDato() {
        return dato;
    }

    public int getTipo() {
        return tipo;
    }
}
