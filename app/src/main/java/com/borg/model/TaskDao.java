package com.borg.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void createUser(Task task);

    @Query("SELECT * FROM task")
    List<Task> readAllTask();

    @Update
    void update(Task task);

    @Query("DELETE FROM task")
    void deleteAllTask();

    @Delete
    void delete(Task task);
}
