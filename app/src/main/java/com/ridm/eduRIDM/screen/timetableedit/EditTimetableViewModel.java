package com.ridm.eduRIDM.screen.timetableedit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class EditTimetableViewModel extends ViewModel {
    List<TimeTable> courseList = new ArrayList<>();
    private final MutableLiveData<Boolean> onSubmit = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<Boolean> getOnSubmit() {
        return onSubmit;
    }

    public void onSubmitClicked() {
        onSubmit.setValue(Boolean.TRUE);
    }

    public void doneReachingProfile() {
        onSubmit.setValue(Boolean.FALSE);
    }
}
