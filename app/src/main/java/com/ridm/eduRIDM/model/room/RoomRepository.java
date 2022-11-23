package com.ridm.eduRIDM.model.room;

import android.app.Application;

import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClass;
import com.ridm.eduRIDM.model.room.Plan.Plan;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RoomRepository {
    private final AppDatabase appDatabase;
    private final Executor executor = Executors.newSingleThreadExecutor();

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

    public void insertEval(Eval eval) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.evalDao().insertEval(eval);
            }
        });
    }

    public List<Eval> getAllEvals() {
        List<Eval> eval = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                eval.addAll(appDatabase.evalDao().getAllEvals());
            }
        });

        return eval;
    }

    public void insertExtraClass(ExtraClass extraClass) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.extraClassDao().insertExtraClass(extraClass);
            }
        });
    }

    public void insertGrades(List<CurrentGrade> currentGradeList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (CurrentGrade currentGrade : currentGradeList) {
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

    public List<Plan> getAllPlans(String date) {
        List<Plan> planList = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                planList.addAll(appDatabase.planDao().getPlanByDate(date));
            }
        });

        return planList;
    }

    public List<Backlog> getBacklogForCourse(String deptCode, String courseCode) {
        List<Backlog> backlogList = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                backlogList.addAll(appDatabase.backlogDao().getBacklogFor(deptCode, courseCode));
            }
        });

        return backlogList;
    }

    public List<TimeTable> getCourses() {
        List<TimeTable> courseList = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseList.addAll(appDatabase.timeTableDao().getAllCourses());
            }
        });

        return courseList;
    }

    public List<TimeTable> getAllClassesByDay(String days) {
        List<TimeTable> classes = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                classes.addAll(appDatabase.timeTableDao().getAllClassByDay(days));
            }
        });

        return classes;
    }

    public List<Eval> getUpcomingEvals(String date1, String date2) {
        List<Eval> upcomingEvalList = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                upcomingEvalList.addAll(appDatabase.evalDao().getUpcomingEvals(date1, date2));
            }
        });

        return upcomingEvalList;
    }
}
