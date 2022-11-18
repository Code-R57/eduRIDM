package com.ridm.eduRIDM.model.room.ExtraClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExtraClassDao {

    @Insert
    void insertExtraClass(ExtraClass extraClass);

    @Delete
    void deleteExtraClass(ExtraClass extraClass);

    @Query("SELECT * FROM ExtraClass where Date = :date")
    List<ExtraClass> getAllExtraClassByDate(String date);
}
