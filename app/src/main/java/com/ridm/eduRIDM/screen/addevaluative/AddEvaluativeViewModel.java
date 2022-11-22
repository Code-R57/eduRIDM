package com.ridm.eduRIDM.screen.addevaluative;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.Plan.Plan;

public class AddEvaluativeViewModel extends ViewModel {

    Eval eval = new Eval();
    private MutableLiveData<Boolean> navigateToMyAcads = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToMyAcads() {
        return navigateToMyAcads;
    }

    public void onSubmit() {

        insertEval();
        navigateToMyAcads.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToMyAcads() {
        navigateToMyAcads.setValue(Boolean.FALSE);
    }

    public void insertEval() {
        MainActivity.roomRepository.insertEval(eval);
    }
}
