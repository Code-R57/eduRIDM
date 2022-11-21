package com.ridm.eduRIDM.screen.timetableedit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditTimetableViewModel extends ViewModel {
    private MutableLiveData<Boolean> onSubmit = new MutableLiveData<>(Boolean.FALSE);

    public MutableLiveData<Boolean> getOnSubmit() {
        return onSubmit;
    }

    public void onSubmitClicked() {
        onSubmit.setValue(Boolean.TRUE);
    }

    public void doneReachingProfile() {
        onSubmit.setValue(Boolean.FALSE);
    }
}
