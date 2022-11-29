package com.ridm.eduRIDM.screen.onboarding;

import static com.ridm.eduRIDM.MainActivity.firebaseQueries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class RegisterViewModel extends ViewModel {

    Map<String, Object> userInfo = new HashMap<>();
    private final MutableLiveData<Boolean> navigateToAddTimetable = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToAddTimetable() {
        return navigateToAddTimetable;
    }

    public void onNavigateToAddTimetableClicked() {
        navigateToAddTimetable.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddTimetable() {
        navigateToAddTimetable.setValue(Boolean.FALSE);
    }

    public void addUserToFirebase(String userKey) {
        firebaseQueries.createUser(userKey, userInfo);
        onNavigateToAddTimetableClicked();
    }
}
