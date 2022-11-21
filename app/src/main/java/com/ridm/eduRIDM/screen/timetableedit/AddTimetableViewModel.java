package com.ridm.eduRIDM.screen.timetableedit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddTimetableViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToHomeScreen = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToHomeScreen() {
        return navigateToHomeScreen;
    }

    public void onSubmitClicked() {
        navigateToHomeScreen.setValue(Boolean.TRUE);
    }
    public void onSkipClicked() {
        navigateToHomeScreen.setValue(Boolean.TRUE);
    }

    public void doneReachingHomeScreen() {
        navigateToHomeScreen.setValue(Boolean.FALSE);
    }
}