package com.ridm.eduRIDM.screen.addevaluative;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddEvaluativeViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToMyAcads = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToMyAcads() {
        return navigateToMyAcads;
    }

    public void onSubmit() {
        navigateToMyAcads.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToMyAcads() {
        navigateToMyAcads.setValue(Boolean.FALSE);
    }
}
