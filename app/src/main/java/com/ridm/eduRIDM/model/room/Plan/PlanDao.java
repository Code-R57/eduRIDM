package com.ridm.eduRIDM.model.room.Plan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanDao {

    @Insert
    void insertPlan(Plan plan);

    @Delete
    void deletePlan(Plan plan);

    @Query("SELECT * FROM `Plan` where Date = :date ORDER BY priority desc, endTime")
    List<Plan> getPlanByDate(String date);
}

