package com.borg.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDao {

    @Insert
    void createClient(Client client);

    @Query("SELECT * FROM client")
    List<Client> readAllClient();

    @Update
    void update(Client client);

    @Query("DELETE FROM client")
    void deleteAllClient();

    @Delete
    void delete(Client client);

    @Query("INSERT INTO client (firstName, address) VALUES(:firstName,:address)")
    void insertNewClient(String firstName, String address);
}
