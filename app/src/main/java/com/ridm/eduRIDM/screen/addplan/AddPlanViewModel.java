package com.ridm.eduRIDM.screen.addplan;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Plan.Plan;

public class AddPlanViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToPlanner = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToPlanner() {
        return navigateToPlanner;
    }

    public void onSubmit() {
        navigateToPlanner.setValue(Boolean.TRUE);
        insertPlan();
    }

    public void doneNavigatingToPlanner() {
        navigateToPlanner.setValue(Boolean.FALSE);
    }

    Plan plan = new Plan();

    public void insertPlan() {
        MainActivity.roomRepository.insertPlan(plan);
    }
}
