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

    interface DialogFragmentEditarMarcadorListener {
        void onAccept(Marcador marcador);
    }

    Marcador marcador;
    LinearLayout layoutPorCompas;
    LinearLayout layoutPorTiempo;
    Switch switchPorCompasTiempo;
    EditText txtHora, txtMinuto, txtSegundo, txtMilisegundo, txtCompasInicio, txtNombre;
    Button btnAceptar, btnCancelar;

    String titulo;
    int compasActual, tiempoActual;

    DialogFragmentEditarMarcadorListener listener;

    public DialogFragmentEditarMarcador(Marcador marcador, String titulo, int compasActual, int tiempoActual, DialogFragmentEditarMarcadorListener listener) {
        this.marcador = marcador;
        this.titulo = titulo;
        this.compasActual = compasActual;
        this.tiempoActual = tiempoActual;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //If marcador is null, then we are creating a new bookmark, if is not, then we are editing
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
        //It makes appear the layouts that have the information of the bookmark start point
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

        //A matrix that describes the limits of milis, seconds, minutes and hours; and the Edit Text
        //asociated
        final Object[][] listaEdits = new Object[][]{
                {txtMilisegundo, 1000},
                {txtSegundo, 60},
                {txtMinuto, 60},
                {txtHora, 597}
        };

        //Every time that a EditText looses focus, this will verify if its value is on the limit, if
        //its not, will send the leftovers to the next EditText, and this Edit text will verify again
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
                //If there are not corrections made to any of the EditTexts, accept the changes
                if (!setTextCorregido(listaEdits, 0) && checkFormato()) {

                    int inicio;
                    String nombre;
                    boolean isTiempo;

                    isTiempo = switchPorCompasTiempo.isChecked();

                    if (isTiempo) {
                        inicio = fromEditsToMilis();

                    } else
                        inicio = Integer.parseInt(txtCompasInicio.getText().toString());

                    nombre = txtNombre.getText().toString();


                    if (marcador == null)
                        marcador = new Marcador(inicio, nombre, isTiempo);
                    else {
                        marcador.setNombre(nombre);
                        marcador.setInicio(inicio);
                        marcador.setIsTiempo(isTiempo);
                    }

                    listener.onAccept(marcador);

                    dismiss();
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //-------------Compas inicializar-----------------------------------------------------------
        //-------------Tiempo inicializar-----------------------------------------------------------
        txtCompasInicio = root.findViewById(R.id.txtCompasInicio_DialogFragmentEditarPulso);

        if (marcador != null) {
            //When editing:
            if (marcador.isTiempo()){
                txtMilisegundo.setText(marcador.getInicio()+"");
                setTextCorregido(listaEdits, 0);}
            else
                txtCompasInicio.setText(marcador.getInicio()+"");
        } else {

            //When creating:
            if (compasActual > 0) txtCompasInicio.setText(String.valueOf(compasActual));
            if (tiempoActual > 0) {
                txtMilisegundo.setText(String.valueOf(tiempoActual));
                setCorrector(txtMilisegundo, 0, listaEdits);
            }
        }

        //----------Edit Text nombre----------------------------------------------------------------
        txtNombre = root.findViewById(R.id.txtNombreMarcador_DialogFragmentAgregarMarcador);

        if (marcador != null)
            txtNombre.setText(marcador.getNombre());


    }

    private boolean checkFormato() {
        if (txtNombre.getText().toString().equals("")) {
            Toast.makeText(getContext(), getResources().getString(R.string.error_nombre_vacio), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (switchPorCompasTiempo.isChecked()) {
            //Por tiempo
            if (fromEditsToMilis() < 0)
                return false;

        } else {


            //por compas
            if (txtCompasInicio.getText().toString().equals("") || Integer.parseInt(txtCompasInicio.getText().toString()) < 1) {
                Toast.makeText(getContext(), getResources().getString(R.string.error_compas), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public void setCorrector(final EditText txtCorrigiendo, final int indice, final Object[][] listaEdits) {
        txtCorrigiendo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Only re format if the EditText has lost focus
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

    private int fromEditsToMilis() {
        int horas;
        int minutos;
        int segundos;
        int milisegundos;

        try {
            horas = Integer.parseInt(txtHora.getText().toString());
        } catch (NumberFormatException e) {
            horas = 0;
        }

        try {
            minutos = Integer.parseInt(txtMinuto.getText().toString());
        } catch (NumberFormatException e) {
            minutos = 0;
        }

        try {
            segundos = Integer.parseInt(txtSegundo.getText().toString());
        } catch (NumberFormatException e) {
            segundos = 0;
        }

        try {
            milisegundos = Integer.parseInt(txtMilisegundo.getText().toString());
        } catch (NumberFormatException e) {
            milisegundos = 0;
        }

        return horas * 3600000 +
                minutos * 60000 +
                segundos * 1000 +
                milisegundos;
    }

    public boolean setTextCorregido(Object[][] listaEditText_Limite, int indiceInicial) {


        int acumulado = 0;

        boolean cambiado = false;

        for (indiceInicial = indiceInicial; indiceInicial < listaEditText_Limite.length; indiceInicial++) {
            EditText txtRevisando = (EditText) listaEditText_Limite[indiceInicial][0];

            int limite = (Integer) listaEditText_Limite[indiceInicial][1];
            int valorRevisando = -1;
            try {
                //The value for the EditText is the leftover of the previous EditText + The actual
                //value
                valorRevisando = acumulado + Integer.parseInt(txtRevisando.getText().toString());

            } catch (NumberFormatException e) {
                //If the actual value is not a number, then it's value is only the leftover of the
                //previous EditText
                valorRevisando = acumulado;
            }


            if (valorRevisando > 0) {

                //True if there are corrections of time format.
                //If the actual value is more than the limit to this time measure, then there are
                //corrections.
                cambiado = cambiado || valorRevisando >= limite;

                //The MOD of the actual value % the limit
                int sobrante = valorRevisando % limite;

                //if there are leftovers that actually fits the time measure, then put this text in
                //the EditText
                if (sobrante > 0)
                    txtRevisando.setText(String.valueOf(sobrante));
                else txtRevisando.setText("");

                //Set the actual leftover to be used in the next EditText correction
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
