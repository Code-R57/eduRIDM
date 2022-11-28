package com.ridm.eduRIDM.screen.myprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToUpdateCGPA = new MutableLiveData<>(Boolean.FALSE);
    private MutableLiveData<Boolean> navigateToEditTT = new MutableLiveData<>(Boolean.FALSE);
    private MutableLiveData<Boolean> navigateToAddExtraClass = new MutableLiveData<>(Boolean.FALSE);

    Date today = new Date();
    String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(today);

    private MutableLiveData<String> date = new MutableLiveData<>(todayDate);
    public LiveData<String> getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.setValue(date);
    }

    List<TimeTable> classList = new ArrayList<>();

    public void getClassesByDay(String days) {
        classList = MainActivity.roomRepository.getAllClassesByDay(days);
    }

    public LiveData<Boolean> getNavigateToUpdateCGPA() {
        return navigateToUpdateCGPA;
    }

    public void onNavigateToUpdateCGPAClicked() {
        navigateToUpdateCGPA.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToUpdateCGPA() {
        navigateToUpdateCGPA.setValue(Boolean.FALSE);
    }

    public LiveData<Boolean> getNavigateToEditTT() {
        return navigateToEditTT;
    }

    public void onNavigateToEditTTClicked() {
        navigateToEditTT.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToEditTT() {
        navigateToEditTT.setValue(Boolean.FALSE);
    }

    public LiveData<Boolean> getNavigateToAddExtraClass() {
        return navigateToAddExtraClass;
    }

    public void onNavigateToAddExtraClass() {
        navigateToAddExtraClass.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddExtraClass() {
        navigateToAddExtraClass.setValue(Boolean.FALSE);
    }
}
