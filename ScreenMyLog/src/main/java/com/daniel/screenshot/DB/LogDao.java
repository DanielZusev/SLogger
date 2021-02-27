package com.daniel.screenshot.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Query("SELECT * FROM Logs")
    List<Log> getAll();

    @Insert
    void insertAll(Log... appUsages);

    @Query("DELETE FROM Logs")
    void deleteAllLogs();
}
