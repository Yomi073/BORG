package com.borg.model.database;

import androidx.room.ColumnInfo;
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

    @Update
    void update(Client client);

    @Query("DELETE FROM client")
    void deleteAllClient();

    @Delete
    void delete(Client client);

    @Query("SELECT * FROM client")
    List<Client> getAllClients();

    @Query("INSERT INTO client (firstName, lastName, address, phoneNumber, email) VALUES(:firstName,:lastName,:address,:phoneNumber,:email)")
    void insertNewClient(String firstName, String lastName, String address, String phoneNumber, String email);
}
