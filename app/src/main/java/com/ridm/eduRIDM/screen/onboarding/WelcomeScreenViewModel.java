package com.ridm.eduRIDM.screen.onboarding;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;

public class WelcomeScreenViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToRegister = new MutableLiveData<>(Boolean.FALSE);

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
