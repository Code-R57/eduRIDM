package com.ridm.eduRIDM.screen.updatecgpa;

import android.util.Log;

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
    float currentSG = 0;

    public void onNavigateToProfileClicked() {
        insertAllGrades();
        navigateToProfile.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToProfile() {
        navigateToProfile.setValue(Boolean.FALSE);
    }

    public void getAllCurrentGrades() {
        currentGradeList = MainActivity.roomRepository.getAllGrades();
    }

    public void calculateSG() {
        int semCreds = 0;
        int semGradePoint = 0;

        for(CurrentGrade currentGrade: currentGradeList) {
            if(getGradePoint(currentGrade.getGrade()) == 0) {
                semCreds += currentGrade.getCredits();
                semGradePoint += getGradePoint(currentGrade.getGrade());
            }
        }

        Log.d("Some Debug Again", currentSG + " " + semGradePoint + " " + semCreds);
        currentSG = (float) semGradePoint / (float) semCreds;
    }

    public void insertAllGrades() {
        MainActivity.roomRepository.insertGrades(currentGradeList);
    }

    public int getGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 10;
            case "A-":
                return 9;
            case "B":
                return 8;
            case "B-":
                return 7;
            case "C":
                return 6;
            case "C-":
                return 5;
            case "D":
                return 4;
            case "E":
                return 3;
            case "NC":
                return 2;
            default:
                return 0;
        }
    }
}
