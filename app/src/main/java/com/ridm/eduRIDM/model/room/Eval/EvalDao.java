package com.ridm.eduRIDM.model.room.Eval;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EvalDao {

    @Insert
    void insertEval(Eval eval);

    @Delete
    void deleteEval(Eval eval);

    @Query("SELECT * FROM Eval where date >= :date ORDER BY date, time")
    List<Eval> getAllEvals(String date);

    @Query("SELECT * FROM Eval WHERE date BETWEEN :date1 AND :date2 ORDER BY date, time")
    List<Eval> getUpcomingEvals(String date1, String date2);
}
