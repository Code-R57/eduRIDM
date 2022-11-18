package com.ridm.eduRIDM.model.room.TimeTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TimeTableDao {

    @Insert
    void insertClass(TimeTable timeTable);

    @Delete
    void deleteClass(TimeTable timeTable);

    @Query("SELECT * FROM TimeTable where Days like :days")
    List<TimeTable> getAllExtraClassByDate(String days);
}
