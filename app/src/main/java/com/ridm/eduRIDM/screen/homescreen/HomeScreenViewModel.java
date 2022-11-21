package com.ridm.eduRIDM.screen.homescreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeScreenViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToStopwatchScreen = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToStopwatchScreen() {
        return navigateToStopwatchScreen;
    }

    public void onNavigateToStopwatchScreen() {
        navigateToStopwatchScreen.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToStopwatchScreen() {
        navigateToStopwatchScreen.setValue(Boolean.FALSE);
    }
}
