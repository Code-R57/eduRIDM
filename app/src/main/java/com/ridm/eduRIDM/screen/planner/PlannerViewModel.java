package com.ridm.eduRIDM.screen.planner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Plan.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlannerViewModel extends ViewModel {

    List<Plan> planList = new ArrayList<>();

    private final MutableLiveData<Boolean> navigateToAddPlan = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getNavigateToAddPlan() {
        return navigateToAddPlan;
    }

    public void onNavigateToAddPlanClicked() {
        navigateToAddPlan.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddPlan() {
        navigateToAddPlan.setValue(Boolean.FALSE);
    }

    public void getAllPlans(String date) {
        planList = MainActivity.roomRepository.getAllPlans(date);
    }
}
