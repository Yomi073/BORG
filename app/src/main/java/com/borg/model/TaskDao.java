package com.borg.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
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

    @Query("SELECT task.id, client.firstName, client.address, task.date  FROM task LEFT JOIN client ON task.client_FK = client.id LEFT JOIN user ON task.user_FK = :i WHERE task.user_FK = :i")
    List<ViewUsersTasks> getUsersTasks(int i);


    @Query("INSERT INTO task (user_FK, client_FK) VALUES(:user_FK,:client_FK)")
    void insertNewTask(int user_FK, int client_FK);


}
