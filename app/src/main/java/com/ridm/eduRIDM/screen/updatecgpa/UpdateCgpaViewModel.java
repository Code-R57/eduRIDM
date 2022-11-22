package com.ridm.eduRIDM.screen.updatecgpa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;

import java.util.ArrayList;
import java.util.List;

public class UpdateCgpaViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToProfile = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToProfile() {
        return navigateToProfile;
    }

    List<CurrentGrade> currentGradeList = new ArrayList<>();

    public void onNavigateToProfileClicked() {
        navigateToProfile.setValue(Boolean.TRUE);
        insertAllGrades();
    }

    public void doneNavigatingToProfile() {
        navigateToProfile.setValue(Boolean.FALSE);
    }

    public void getAllCurrentGrades() {
        currentGradeList = MainActivity.roomRepository.getAllGrades();
    }

    public void insertAllGrades() {
        MainActivity.roomRepository.insertGrades(currentGradeList);
    }
}
