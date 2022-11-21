package com.ridm.eduRIDM.screen.onboarding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToAddTimetable = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToAddTimetable() {
        return navigateToAddTimetable;
    }

    public void onNavigateToAddTimetableClicked() {
        navigateToAddTimetable.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddTimetable() {
        navigateToAddTimetable.setValue(Boolean.FALSE);
    }
}
