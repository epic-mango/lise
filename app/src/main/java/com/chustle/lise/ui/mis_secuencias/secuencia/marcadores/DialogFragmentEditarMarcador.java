package com.chustle.lise.ui.mis_secuencias.secuencia.marcadores;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Marcador;

public class DialogFragmentEditarMarcador extends DialogFragment {

    Marcador marcador;
    LinearLayout layoutPorCompas;
    LinearLayout layoutPorTiempo;
    Switch switchPorCompasTiempo;
    EditText txtHora, txtMinuto, txtSegundo, txtMilisegundo;
    Button btnAceptar, btnCancelar;

    String titulo;

    public DialogFragmentEditarMarcador(Marcador marcador, String titulo) {
        this.marcador = marcador;
        this.titulo = titulo;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (marcador == null) builder.setTitle(getString(R.string.agregar_marcador) + " " + titulo);
        else builder.setTitle(getString(R.string.editar_marcador) + " " + titulo);

        View root = requireActivity().getLayoutInflater()
                .inflate(R.layout.dialog_fragment_editar_marcador, null);

        inicializarComponentes(root);

        builder.setView(root);

        return builder.create();

    }

    private void inicializarComponentes(View root) {
        layoutPorCompas = root.findViewById(R.id.layoutPorCompas_DialogFragmentEditarMarcador);
        layoutPorTiempo = root.findViewById(R.id.layoutPorTiempo_DialogFragmentEditarMarcador);
        switchPorCompasTiempo = root.findViewById(R.id.switchCompasTiempo_DialogFragmentEditarMarcador);


        //---------------------Switch bar/tempo------------------------------------------------------
        switchPorCompasTiempo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    layoutPorCompas.setVisibility(View.GONE);
                    layoutPorTiempo.setVisibility(View.VISIBLE);
                } else {
                    layoutPorCompas.setVisibility(View.VISIBLE);
                    layoutPorTiempo.setVisibility(View.GONE);
                }


            }
        });

        if (marcador != null)
            switchPorCompasTiempo.setChecked(marcador.isTiempo());

        if (switchPorCompasTiempo.isChecked()) {

            layoutPorCompas.setVisibility(View.GONE);
            layoutPorTiempo.setVisibility(View.VISIBLE);
        } else {
            layoutPorCompas.setVisibility(View.VISIBLE);
            layoutPorTiempo.setVisibility(View.GONE);
        }

        //---------------------------------Time edits--------------------------------------------
        txtMilisegundo = root.findViewById(R.id.txtMilisegundoInicio_DialogFragmentAgregarMarcador);
        txtSegundo = root.findViewById(R.id.txtSegundoInicio_DialogFragmentAgregarMarcador);
        txtMinuto = root.findViewById(R.id.txtMinutoInicio_DialogFragmentAgregarMarcador);
        txtHora = root.findViewById(R.id.txtHoraInicio_DialogFragmentAgregarMarcador);


        final Object[][] listaEdits = new Object[][]{
                {txtMilisegundo, 1000},
                {txtSegundo, 60},
                {txtMinuto, 60},
                {txtHora, 597}
        };

        setCorrector(txtMilisegundo, 0, listaEdits);
        setCorrector(txtSegundo, 1, listaEdits);
        setCorrector(txtMinuto, 2, listaEdits);
        setCorrector(txtHora, 3, listaEdits);
        //------------Buttons-----------------------------------------------------------------------

        btnAceptar = root.findViewById(R.id.btnAceptar_dialogFragmentEditarMarcador);
        btnCancelar = root.findViewById(R.id.btnCancelar_dialogFragmentEditarMarcador);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!setTextCorregido(listaEdits, 0))
                    dismiss();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setCorrector(final EditText txtCorrigiendo, final int indice, final Object[][] listaEdits) {
        txtCorrigiendo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setTextCorregido(listaEdits, indice);
                }
            }
        });

        txtCorrigiendo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(""))
                    try {
                        Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        txtCorrigiendo.setText(s.toString().substring(0, s.toString().length() - 1));
                    }
            }
        });
    }

    public boolean setTextCorregido(Object[][] listaEditText_Limite, int indiceInicial) {
        int acumulado = 0;

        boolean cambiado = false;

        for (indiceInicial = indiceInicial; indiceInicial < listaEditText_Limite.length; indiceInicial++) {
            EditText txtRevisando = (EditText) listaEditText_Limite[indiceInicial][0];
            int limite = (Integer) listaEditText_Limite[indiceInicial][1];
            int valorRevisando = -1;
            try {
                valorRevisando = acumulado + Integer.parseInt(txtRevisando.getText().toString());

            } catch (NumberFormatException e) {
                valorRevisando = acumulado;
            }

            if (valorRevisando > 0) {

                cambiado = cambiado || valorRevisando >= limite;

                int sobrante = valorRevisando % limite;
                if (sobrante > 0)
                    txtRevisando.setText(sobrante + "");
                else txtRevisando.setText("");

                acumulado = valorRevisando / limite;


            } else {
                txtRevisando.setText("");

            }
        }

        if (cambiado)
            Toast.makeText(getContext(), getString(R.string.error_formato_hora), Toast.LENGTH_SHORT).show();
        return cambiado;
    }
}
