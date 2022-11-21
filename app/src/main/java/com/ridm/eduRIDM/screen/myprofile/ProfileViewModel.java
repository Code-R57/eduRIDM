package com.ridm.eduRIDM.screen.myprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToUpdateCGPA = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToUpdateCGPA() {
        return navigateToUpdateCGPA;
    }

    public void onNavigateToUpdateCGPAClicked() {
        navigateToUpdateCGPA.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToUpdateCGPA() {
        navigateToUpdateCGPA.setValue(Boolean.FALSE);
    }
}
