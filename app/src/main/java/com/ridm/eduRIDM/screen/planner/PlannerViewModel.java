package com.ridm.eduRIDM.screen.planner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlannerViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToAddPlan = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToAddPlan() {
        return navigateToAddPlan;
    }

    public void onNavigateToAddPlanClicked() {
        navigateToAddPlan.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddPlan() {
        navigateToAddPlan.setValue(Boolean.FALSE);
    }
}
