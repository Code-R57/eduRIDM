package com.ridm.eduRIDM.screen.timetableedit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class AddTimetableViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToHomeScreen = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToHomeScreen() {
        return navigateToHomeScreen;
    }

    List<TimeTable> currentCourseList = new ArrayList<>();

    public void onSubmitClicked() {
        navigateToHomeScreen.setValue(Boolean.TRUE);
    }
    public void onSkipClicked() {
        navigateToHomeScreen.setValue(Boolean.TRUE);
    }

    public void doneReachingHomeScreen() {
        navigateToHomeScreen.setValue(Boolean.FALSE);
    }

    List<QueryDocumentSnapshot> coursesList = new ArrayList<>();
}