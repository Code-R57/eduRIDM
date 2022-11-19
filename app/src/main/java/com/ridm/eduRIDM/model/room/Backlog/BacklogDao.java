package com.ridm.eduRIDM.model.room.Backlog;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BacklogDao {

    @Insert
    void insertBacklog(Backlog backlog);

    @Delete
    void deleteBacklog(Backlog backlog);

    @Query("SELECT * FROM Backlog")
    List<Backlog> getAllBacklogs();
}
