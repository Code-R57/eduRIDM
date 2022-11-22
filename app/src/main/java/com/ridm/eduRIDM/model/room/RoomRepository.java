package com.ridm.eduRIDM.model.room;

import android.app.Application;

import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;
import com.ridm.eduRIDM.model.room.Plan.Plan;
import com.ridm.eduRIDM.model.room.Plan.PlanDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RoomRepository {
    private AppDatabase appDatabase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public RoomRepository(Application application) {
        DatabaseClient databaseClient = DatabaseClient.getInstance(application);
        appDatabase = databaseClient.getAppDatabase();
    }

    public void insertPlan(Plan plan) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.planDao().insertPlan(plan);
            }
        });
    }

    public void insertGrades(List<CurrentGrade> currentGradeList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(CurrentGrade currentGrade: currentGradeList) {
                    appDatabase.currentGradeDao().insertCurrentGrade(currentGrade);
                }
            }
        });
    }

    public List<CurrentGrade> getAllGrades() {
        List<CurrentGrade> currentGradeList = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                currentGradeList.addAll(appDatabase.currentGradeDao().getAllCurrentGrades());
            }
        });

        return currentGradeList;
    }
}
