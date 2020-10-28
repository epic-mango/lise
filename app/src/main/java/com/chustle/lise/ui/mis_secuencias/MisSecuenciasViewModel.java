package com.chustle.lise.ui.mis_secuencias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisSecuenciasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MisSecuenciasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is \"mis secuencias\" fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}