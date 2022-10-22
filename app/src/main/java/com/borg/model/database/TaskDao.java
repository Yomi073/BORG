package com.borg.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
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

    @Query("SELECT * FROM task")
    List<ViewAdminTasks> getAllTasks();

    @Query("SELECT firstName FROM user LEFT JOIN task ON user.id = :user_FK WHERE user.id = :user_FK")
    String getUserNameByFK(Integer user_FK);

    @Query("SELECT firstName FROM client LEFT JOIN task ON client.id = :client_FK WHERE client.id = :client_FK")
    String getClientNameByFK(Integer client_FK);

    @Query("SELECT task.id, client.firstName, client.address, task.date  FROM task LEFT JOIN client ON task.client_FK = client.id LEFT JOIN user ON task.user_FK = user.id WHERE task.user_FK = :i")
    List<ViewUserTasks> getUserTasks(int i);

    @Query("INSERT INTO task (user_FK, client_FK, date) VALUES(:user_FK,:client_FK,:date)")
    void insertNewTask(int user_FK, int client_FK, Date date);


}