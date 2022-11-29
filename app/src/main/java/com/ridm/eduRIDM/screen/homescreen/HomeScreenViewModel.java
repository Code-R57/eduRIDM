package com.ridm.eduRIDM.screen.homescreen;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeScreenViewModel extends ViewModel {
    List<Eval> upcomingEvalList = new ArrayList<>();
    List<TimeTable> classList = new ArrayList<>();
    HashMap<String, Boolean> backlogMap = new HashMap<>();
    private final MutableLiveData<Boolean> navigateToStopwatchScreen = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<String> currentSelection = new MutableLiveData<>("Today");
    private final MutableLiveData<Boolean> classMissed = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToStopwatchScreen() {
        return navigateToStopwatchScreen;
    }

    public LiveData<String> getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(String currentSelection) {
        this.currentSelection.setValue(currentSelection);
    }

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

    public void getBacklogForDateAndCourse(String courseName, String section, String date) {
        List<Backlog> backlogList = MainActivity.roomRepository.getBacklogByDate(courseName, section, date);
        backlogMap.put(courseName + " " + section, Boolean.TRUE);
    }

    public void getBacklogs(String date) {
        for (TimeTable course : classList) {
            getBacklogForDateAndCourse(course.getCourseName(), course.getSection(), date);
        }
    }
}
