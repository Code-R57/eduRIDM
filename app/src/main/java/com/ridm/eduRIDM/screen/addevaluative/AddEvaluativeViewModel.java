package com.ridm.eduRIDM.screen.addevaluative;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.DistinctClasses;

import java.util.ArrayList;
import java.util.List;

public class AddEvaluativeViewModel extends ViewModel {

    Eval eval = new Eval();

    private MutableLiveData<Boolean> navigateToMyAcads = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToMyAcads() {
        return navigateToMyAcads;
    }

    List<DistinctClasses> courseList = new ArrayList<>();

    public void onNavigateToMyAcadsClicked() {
        insertEval();
        navigateToMyAcads.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToMyAcads() {
        navigateToMyAcads.setValue(Boolean.FALSE);
    }

    public void insertEval() {
        MainActivity.roomRepository.insertEval(eval);
    }

    public void getMyCourses() {
        courseList = MainActivity.roomRepository.getDistinctCourses();
    }
}
