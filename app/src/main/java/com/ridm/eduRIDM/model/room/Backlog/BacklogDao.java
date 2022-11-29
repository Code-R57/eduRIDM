package com.ridm.eduRIDM.model.room.Backlog;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ridm.eduRIDM.model.room.TimeTable.DistinctClasses;

import java.util.List;

@Dao
public interface BacklogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBacklog(Backlog backlog);

    @Delete
    void deleteBacklog(Backlog backlog);

    @Query("SELECT * FROM Backlog")
    List<Backlog> getAllBacklogs();

    @Query("SELECT * FROM Backlog where deptCode = :deptCode AND courseCode = :courseCode")
    List<Backlog> getBacklogFor(String deptCode, String courseCode);

    @Query("SELECT DISTINCT courseName, courseCode, deptCode FROM Backlog")
    List<DistinctClasses> getDistinctCourses();

    @Query("SELECT * FROM Backlog WHERE courseName = :courseName AND date = :date")
    List<Backlog> getBacklogForCourseAndDate(String courseName, String date);
}
