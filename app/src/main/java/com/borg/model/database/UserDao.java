package com.borg.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void createUser(User user);

    @Query("SELECT * FROM user")
    List<User> readAllUsers();

    @Update
    void update(User user);

    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    List<ViewAdminUsers> getAllUsers();

    @Query("SELECT name FROM role LEFT JOIN user ON role.id = :role_FK")
    String getRoleByFK(Integer role_FK);


    @Query("INSERT INTO user (firstName,lastName,address,phoneNumber,email,userName,password,role_FK) VALUES(:firstName,:lastName,:address,:phoneNumber,:email,:userName,:password, :role_FK)")
    void insertNewUser(String firstName,String lastName,String address,String phoneNumber,String email,String userName,String password, Integer role_FK);

    @Query("SELECT userName FROM user WHERE userName=:Username")
    String findUser(String Username);

    @Query("SELECT * FROM user WHERE userName=:Username AND password=:Password")
    List<User> findUser(String Username, String Password);

    @Query("SELECT role.name FROM role LEFT JOIN user ON role.id=user.Role_FK WHERE userName=:Username")
    String findRoleNameByUserName(String Username);

    @Query("SELECT id FROM user WHERE userName=:Username")
    int getUserIdByUsername(String Username);


}
