package com.chustle.lise.ui.mis_secuencias;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Secuencia;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSecuencia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSecuencia extends Fragment {

    public FragmentSecuencia() {
        // Required empty public constructor
    }

    Secuencia secuencia;

    public static FragmentSecuencia newInstance(Secuencia secuencia) {
        FragmentSecuencia fragment = new FragmentSecuencia();
        fragment.secuencia = secuencia;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_secuencia, container, false);

        Bundle bundle = getArguments();

        String nombre = bundle.getString(SecuenciaListModel.NOMBRE_SECUENCIA);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(nombre);

        return root;
    }
}