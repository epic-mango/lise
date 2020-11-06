package com.chustle.lise.files;

import android.content.Context;

import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.mis_secuencias.ListModelSecuencias;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {
    protected static File FILES_DIR;
    protected Gson gson;

    public Files(Context context) {
        this.FILES_DIR = context.getFilesDir();


        //Object to convert from JSON to Java Object
        gson = new Gson();
    }


    public List<ListModelSecuencias> getSecuenciasListModel() {

        List<ListModelSecuencias> listaSecuencias = new ArrayList<>();

        //An array that lists the file names of the Files Dir
        String[] list = FILES_DIR.list();


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
                ListModelSecuencias listModelSecuencias = new ListModelSecuencias(secuencia.getNombreSecuencia(),
                        secuencia.getArtistaSecuencia(), file);


                listaSecuencias.add(listModelSecuencias);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return listaSecuencias;
    }

}
