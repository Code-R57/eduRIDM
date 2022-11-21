package com.ridm.eduRIDM.screen.addplan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddPlanViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToPlanner = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToPlanner() {
        return navigateToPlanner;
    }

    public void onSubmit() {
        navigateToPlanner.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToPlanner() {
        navigateToPlanner.setValue(Boolean.FALSE);
    }

}
