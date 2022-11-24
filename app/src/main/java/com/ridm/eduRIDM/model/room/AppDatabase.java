package com.ridm.eduRIDM.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.Backlog.BacklogDao;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGradeDao;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.Eval.EvalDao;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClass;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClassDao;
import com.ridm.eduRIDM.model.room.Plan.Plan;
import com.ridm.eduRIDM.model.room.Plan.PlanDao;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTableDao;

@Database(entities = {Backlog.class, CurrentGrade.class, Eval.class, ExtraClass.class, Plan.class, TimeTable.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BacklogDao backlogDao();

    public abstract CurrentGradeDao currentGradeDao();

    public abstract EvalDao evalDao();

    public abstract ExtraClassDao extraClassDao();

    public abstract PlanDao planDao();

    public abstract TimeTableDao timeTableDao();
}
