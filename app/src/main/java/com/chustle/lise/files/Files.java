package com.chustle.lise.files;

import android.content.Context;
import android.util.Log;

import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.mis_secuencias.SecuenciaListModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Files {
    public static File FILES_DIR;

    public Files(Context context) {
        this.FILES_DIR = context.getFilesDir();
    }


    public List<SecuenciaListModel> getSecuencias() {

        List<SecuenciaListModel> listaSecuencias = new ArrayList<>();

        //An array that lists the file names of the Files Dir
        String[] list = FILES_DIR.list();

        //Object to convert from JSON to Java Object
        Gson gson = new Gson();

        //File and BufferedReaders iterator to the files list
        File fileSecuencia;
        BufferedReader br;
        for (String file : list) {

            //Get the file from list
            fileSecuencia = new File(FILES_DIR, file);
            try {
                //prepare te Reader
                br = new BufferedReader(new FileReader(fileSecuencia));

                //Read line and transform from JSON string to Java Object
                Secuencia secuencia = gson.fromJson(br.readLine(), Secuencia.class);

                //Convert to ListModel to display on the RecyclerView
                SecuenciaListModel secuenciaListModel = new SecuenciaListModel(secuencia.getNombreSecuencia(),
                        secuencia.getArtistaSecuencia(), file);


                listaSecuencias.add(secuenciaListModel);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return listaSecuencias;
    }

    public Secuencia crearSecuencia(String nombre, String artista) {

        //Sequence ID will be the moment when it was created
        // El ID de la secuencia es el momento en que se creó
        long now = Calendar.getInstance().getTimeInMillis();

        //LLenamos el objeto secuencia para retornarlo
        //Filling the sequence (Secuencia) object
        Secuencia secuencia = new Secuencia();
        secuencia.setNombreSecuencia(nombre);
        secuencia.setArtistaSecuencia(artista);
        secuencia.setIdSecuencia(now);

        //Creamos un archivo para la secuencia
        //Create a file to save the sequence object
        File fileSecuencia = new File(FILES_DIR, now + ".json");

        //El objeto Gson nos facilita convertir a JSON
        //Gson makes it easy to convert an Java Object to JSON String
        Gson gson = new Gson();

        try {
            if (fileSecuencia.createNewFile()) {
                //The file is succesfully created
                // Se creó correctamente el archivo

                //Creamos un escritor para el archivo
                //Writer for the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileSecuencia, false));

                //Converting the JavaObject to JSON string
                //Convertimos el objeto java a una cadena JSON
                String secuenciaJSON = gson.toJson(secuencia);


                writer.write(secuenciaJSON);
                writer.close();


                return secuencia;
            } else {

                throw new IOException("Error al crear el archivo");
            }
        } catch (IOException e) {
            Log.println(Log.ERROR, "Files.java", e.getMessage());
        }

        return null;

    }
}
