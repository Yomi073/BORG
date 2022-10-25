package com.borg.model.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoleDao {

    @Query("SELECT * FROM role")
    List<Role> getAllRoles();

    @Query("INSERT INTO role VALUES (:id, :name)")
    void insertRole(Integer id, String name);

}
