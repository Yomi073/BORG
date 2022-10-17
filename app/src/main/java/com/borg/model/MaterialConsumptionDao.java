package com.borg.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MaterialConsumptionDao {

    @Insert
    void createMaterialConsumption(MaterialConsumption materialConsumption);

    @Query("SELECT * FROM materialConsumption")
    List<MaterialConsumption> readAllMaterialConsumption();

    @Update
    void update(MaterialConsumption materialConsumption);

    @Query("DELETE FROM materialConsumption")
    void deleteAllMaterialConsumption();

    @Delete
    void delete(MaterialConsumption materialConsumption);

}
