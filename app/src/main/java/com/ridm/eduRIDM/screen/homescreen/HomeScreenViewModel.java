package com.ridm.eduRIDM.screen.homescreen;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToStopwatchScreen = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToStopwatchScreen() {
        return navigateToStopwatchScreen;
    }

    List<Eval> upcomingEvalList = new ArrayList<>();
    List<TimeTable> classList = new ArrayList<>();

    private MutableLiveData<String> currentSelection = new MutableLiveData<>("Today");
    public LiveData<String> getCurrentSelection() {
        return currentSelection;
    }

    private MutableLiveData<Boolean> classMissed = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getClassMissed() {
        return classMissed;
    }

    public void onNavigateToStopwatchScreen() {
        navigateToStopwatchScreen.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToStopwatchScreen() {
        navigateToStopwatchScreen.setValue(Boolean.FALSE);
    }

    public void getUpcomingEvals(String date1, String date2) {
       upcomingEvalList = MainActivity.roomRepository.getUpcomingEvals(date1, date2);
    }

    public void getAllEvals(String date) {
        upcomingEvalList = MainActivity.roomRepository.getAllEvals(date);
    }

    public void getClassesByDay(String days) {
        classList = MainActivity.roomRepository.getAllClassesByDay(days);
    }

    public void setCurrentSelection(String currentSelection) {
        this.currentSelection.setValue(currentSelection);
    }

}
