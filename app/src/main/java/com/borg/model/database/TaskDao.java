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

    @Query("DELETE FROM task WHERE task.id = :id")
    void deleteTaskId(int id);

    @Query("SELECT firstName FROM user LEFT JOIN task ON user.id = :user_FK WHERE user.id = :user_FK")
    String getUserNameByFK(Integer user_FK);

    @Query("SELECT firstName FROM client LEFT JOIN task ON client.id = :client_FK WHERE client.id = :client_FK")
    String getClientNameByFK(Integer client_FK);
/*
    @Query("SELECT task.id, client.firstName, client.address, task.date  FROM task LEFT JOIN client ON task.client_FK = client.id LEFT JOIN user ON task.user_FK = user.id WHERE task.user_FK = :i")
    List<ViewUserTasks> getUserTasks(int i);
*/
    @Query("SELECT " +
            "task.id AS task_id, " +
            "task.date AS task_date, " +
            "task.user_FK AS user_id, " +
            "task.client_FK AS client_id, " +
            "user.firstName AS user_firstName, " +
            "client.firstName AS client_firstName, " +
            "client.address AS client_address, " +
            "client.phoneNumber AS client_phoneNumber " +
            "FROM task LEFT JOIN client ON task.client_FK = client.id LEFT JOIN user ON task.user_FK = user.id WHERE task.user_FK = :i")
    List<ViewUserTasks> getUserTasks(int i);

    @Query("SELECT " +
            "task.id AS task_id, " +
            "task.date AS task_date, " +
            "task.user_FK AS user_id, " +
            "task.client_FK AS client_id, " +
            "user.firstName AS user_firstName, " +
            "client.firstName AS client_firstName, " +
            "client.address AS client_address, " +
            "client.phoneNumber AS client_phoneNumber " +
            "FROM task LEFT JOIN client ON task.client_FK = client.id LEFT JOIN user ON task.user_FK = user.id")
    List<ViewUserTasks> getAllTasks();

    @Query("INSERT INTO task (user_FK, client_FK, date) VALUES(:user_FK,:client_FK,:date)")
    void insertNewTask(int user_FK, int client_FK, Date date);


}
