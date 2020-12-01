package com.chustle.lise.files;

import android.content.Context;
import android.util.Log;

import com.chustle.lise.files.models.Marcador;
import com.chustle.lise.files.models.PistaMarcadores;
import com.chustle.lise.files.models.Secuencia;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class FileSecuencia extends Files {

    public FileSecuencia(Context context) {
        super(context);
    }


    public Secuencia guardarSecuencia(Secuencia secuencia) {

        //Creamos un archivo para la secuencia
        //Create a file to save the sequence object
        File fileSecuencia = new File(FILES_DIR, secuencia.getIdSecuencia() + ".json");

        //El objeto Gson nos facilita convertir a cadena JSON
        //Gson makes it easy to convert an Java Object to JSON String
        Gson gson = new Gson();

        try {
            //Creamos un escritor para el archivo
            //Writer for the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileSecuencia, false));


            //Escribimos la hora de guardado
            //Write the saving time
            secuencia.setVersionSecuencia(Calendar.getInstance().getTimeInMillis());

            //Converting the JavaObject to JSON string
            //Convertimos el objeto java a una cadena JSON
            String secuenciaJSON = gson.toJson(secuencia);


            writer.write(secuenciaJSON);
            writer.close();


            return secuencia;
        } catch (IOException e) {
            Log.println(Log.ERROR, "Files.java", e.getMessage());
        }

        return null;
    }

    //Returns a Java Object, instance of the File from internal device storage
    public Secuencia getSecuencia(String fileName) {
        try {
            String fileJson = new BufferedReader(new FileReader(FILES_DIR + "/" + fileName)).readLine();
            Secuencia secuencia = gson.fromJson(fileJson, Secuencia.class);

            return secuencia;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
