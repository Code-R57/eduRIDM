package com.ridm.eduRIDM.screen.myacads;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.DistinctClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAcadsViewModel extends ViewModel {

    List<Eval> evalList = new ArrayList<>();
    MutableLiveData<List<DistinctClasses>> courses = new MutableLiveData<>();
    HashMap<String, List<Backlog>> courseBacklog = new HashMap<>();

    private final MutableLiveData<Boolean> navigateToAddEval = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<String> currentSelection = new MutableLiveData<>("Evals");

    public LiveData<Boolean> getNavigateToAddEval() {
        return navigateToAddEval;
    }

    public LiveData<String> getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(String currentSelection) {
        this.currentSelection.setValue(currentSelection);
    }

    public void onNavigateToAddEvalClicked() {
        navigateToAddEval.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddEval() {
        navigateToAddEval.setValue(Boolean.FALSE);
    }

    public void getAllEvals() {
        evalList = MainActivity.roomRepository.getAllEvals();
    }

    public void getDistinctCourses() {
        courses.setValue(MainActivity.roomRepository.getDistinctBacklogCourses());
    }

    public void getBacklogForCourse(String deptCode, String courseCode, String courseName) {
        List<Backlog> backlogList = MainActivity.roomRepository.getBacklogForCourse(deptCode, courseCode);
        courseBacklog.put(courseName, backlogList);
    }

    public void getBacklogs() {
        for (DistinctClasses course : courses.getValue()) {
            getBacklogForCourse(course.getDeptCode(), course.getCourseCode(), course.getCourseName());
        }
    }
}
