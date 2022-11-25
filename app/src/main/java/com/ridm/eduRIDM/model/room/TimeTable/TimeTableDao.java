package com.ridm.eduRIDM.model.room.TimeTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TimeTableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClass(TimeTable timeTable);

    @Delete
    void deleteClass(TimeTable timeTable);

    @Query("SELECT * FROM TimeTable where Days like :days ORDER BY time")
    List<TimeTable> getAllClassByDay(String days);

    @Query("SELECT * FROM TimeTable")
    List<TimeTable> getAllCourses();
}
