package com.ridm.eduRIDM.screen.updatecgpa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpdateCGPAViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToProfile = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToProfile() {
        return navigateToProfile;
    }

    public void onNavigateToProfileClicked() {
        navigateToProfile.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToProfile() {
        navigateToProfile.setValue(Boolean.FALSE);
    }
}
