package com.ridm.eduRIDM.screen.myprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToUpdateCGPA = new MutableLiveData<>(Boolean.FALSE);
    private MutableLiveData<Boolean> navigateToEditTT = new MutableLiveData<>(Boolean.FALSE);
    private MutableLiveData<Boolean> navigateToAddExtraClass = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToUpdateCGPA() {
        return navigateToUpdateCGPA;
    }

    public void onNavigateToUpdateCGPAClicked() {
        navigateToUpdateCGPA.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToUpdateCGPA() {
        navigateToUpdateCGPA.setValue(Boolean.FALSE);
    }

    public LiveData<Boolean> getNavigateToEditTT() {
        return navigateToEditTT;
    }

    public void onNavigateToEditTTClicked() {
        navigateToEditTT.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToEditTT() {
        navigateToEditTT.setValue(Boolean.FALSE);
    }

    public LiveData<Boolean> getNavigateToAddExtraClass() {
        return navigateToAddExtraClass;
    }

    public void onNavigateToAddExtraClass() {
        navigateToAddExtraClass.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddExtraClass() {
        navigateToAddExtraClass.setValue(Boolean.FALSE);
    }
}
