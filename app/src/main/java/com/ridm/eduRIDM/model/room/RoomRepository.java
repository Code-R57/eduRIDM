package com.ridm.eduRIDM.model.room;

import android.app.Application;

import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClass;
import com.ridm.eduRIDM.model.room.Plan.Plan;
import com.ridm.eduRIDM.model.room.Plan.PlanDao;

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
    public void insertEval(Eval eval){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.evalDao().insertEval(eval);
            }
        });
    }
    public void insertExtraClass(ExtraClass extraClass){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.extraClassDao().insertExtraClass(extraClass);
            }
        });
    }

}
