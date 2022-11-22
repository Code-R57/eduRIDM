package com.ridm.eduRIDM.model.room.CurrentGrade;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrentGradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrentGrade(CurrentGrade currentGrade);

    @Query("SELECT * FROM CurrentGrade")
    List<CurrentGrade> getAllCurrentGrades();
}
