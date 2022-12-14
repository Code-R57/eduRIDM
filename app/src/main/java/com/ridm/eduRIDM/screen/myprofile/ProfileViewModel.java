package com.ridm.eduRIDM.screen.myprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    Date today = new Date();
    String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
    private final MutableLiveData<String> date = new MutableLiveData<>(todayDate);
    HashMap<String, Boolean> backlogMap = new HashMap<>();
    List<TimeTable> classList = new ArrayList<>();
    private final MutableLiveData<Boolean> navigateToUpdateCGPA = new MutableLiveData<>(Boolean.FALSE);
    //    private MutableLiveData<Boolean> navigateToEditTT = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> navigateToAddExtraClass = new MutableLiveData<>(Boolean.FALSE);

    public LiveData<String> getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.setValue(date);
    }

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

//    public LiveData<Boolean> getNavigateToEditTT() {
//        return navigateToEditTT;
//    }

//    public void onNavigateToEditTTClicked() {
//        navigateToEditTT.setValue(Boolean.TRUE);
//    }

//    public void doneNavigatingToEditTT() {
//        navigateToEditTT.setValue(Boolean.FALSE);
//    }

    public LiveData<Boolean> getNavigateToAddExtraClass() {
        return navigateToAddExtraClass;
    }

    public void onNavigateToAddExtraClass() {
        navigateToAddExtraClass.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToAddExtraClass() {
        navigateToAddExtraClass.setValue(Boolean.FALSE);
    }

//    public void getBacklogsByDate(String date) {
//        List<Backlog> backlogList = MainActivity.roomRepository.getBacklogByDate(date);
//
//        for(Backlog backlog: backlogList) {
//            backlogMap.put(backlog.getCourseName() + " " + backlog.getSection(), Boolean.TRUE);
//        }
//    }
}
