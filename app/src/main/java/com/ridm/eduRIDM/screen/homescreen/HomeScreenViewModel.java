package com.ridm.eduRIDM.screen.homescreen;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToStopwatchScreen = new MutableLiveData<>(Boolean.FALSE);
    public LiveData<Boolean> getNavigateToStopwatchScreen() {
        return navigateToStopwatchScreen;
    }

    List<Eval> upcomingEvalList = new ArrayList<>();
    List<TimeTable> classList = new ArrayList<>();

    public void onNavigateToStopwatchScreen() {
        navigateToStopwatchScreen.setValue(Boolean.TRUE);
    }

    public void doneNavigatingToStopwatchScreen() {
        navigateToStopwatchScreen.setValue(Boolean.FALSE);
    }

    public void getUpcomingEvals(String date1, String date2) {
       upcomingEvalList = MainActivity.roomRepository.getUpcomingEvals(date1, date2);
       Log.d("upcomingEvals",upcomingEvalList.toString());
    }

    public void getAllEvals() {
        upcomingEvalList = MainActivity.roomRepository.getAllEvals();
        Log.d("upcomingEvals",upcomingEvalList.toString());
    }

    public void getClassesToday(String days) {
        classList = MainActivity.roomRepository.getAllClassesByDay(days);
    }

}
