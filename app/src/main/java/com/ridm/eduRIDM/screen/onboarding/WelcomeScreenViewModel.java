package com.ridm.eduRIDM.screen.onboarding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WelcomeScreenViewModel extends ViewModel {

    private final MutableLiveData<Boolean> navigateToRegister = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToRegister() {
        return navigateToRegister;
    }

    public void onNavigateToRegisterClicked() {
        navigateToRegister.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToRegister() {
        navigateToRegister.setValue(Boolean.FALSE);
    }
}
