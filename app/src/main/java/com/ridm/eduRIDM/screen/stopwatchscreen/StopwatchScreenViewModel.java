package com.ridm.eduRIDM.screen.stopwatchscreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StopwatchScreenViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToHomePage = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToHomePage() {
        return navigateToHomePage;
    }

    public void onNavigateToHomePageClicked() {
        navigateToHomePage.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToHomePage() {
        navigateToHomePage.setValue(Boolean.FALSE);
    }
}
