package com.ridm.eduRIDM.screen.myacads;

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

public class MyAcadsViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToAddEval = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToAddEval() {
        return navigateToAddEval;
    }

    private MutableLiveData<String> currentSelection = new MutableLiveData<>("Evals");
    public LiveData<String> getCurrentSelection() {
        return currentSelection;
    }

    List<Eval> evalList = new ArrayList<>();

    List<TimeTable> courses = new ArrayList<>();

    HashMap<String, List<Backlog>> courseBacklog = new HashMap<>();

    public void onNavigateToAddEvalClicked() {
        navigateToAddEval.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddEval() {
        navigateToAddEval.setValue(Boolean.FALSE);
    }

    public void getAllEvals() {
        evalList = MainActivity.roomRepository.getAllEvals();
    }

    public void getAllCourses() {
        courses = MainActivity.roomRepository.getCourses();
    }

    public void getBacklogForCourse(String deptCode, String courseCode, String courseName) {
        courseBacklog.put(courseName, MainActivity.roomRepository.getBacklogForCourse(deptCode, courseCode));
    }

    public void setCurrentSelection(String currentSelection) {
        this.currentSelection.setValue(currentSelection);
    }
}
