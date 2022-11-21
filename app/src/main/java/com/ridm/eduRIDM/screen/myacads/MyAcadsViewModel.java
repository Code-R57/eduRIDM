package com.ridm.eduRIDM.screen.myacads;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyAcadsViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToAddEval = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToAddEval() {
        return navigateToAddEval;
    }

    public void onNavigateToAddEvalClicked() {
        navigateToAddEval.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddEval() {
        navigateToAddEval.setValue(Boolean.FALSE);
    }
}
