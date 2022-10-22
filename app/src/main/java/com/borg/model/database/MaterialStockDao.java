package com.borg.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MaterialStockDao {

    @Insert
    void createMaterialStock(MaterialStock materialStock);

    @Update
    void update(MaterialStock materialStock);

    @Query("DELETE FROM materialStock")
    void deleteAllMaterialStock();

    @Delete
    void delete(MaterialStock materialStock);

    @Query("SELECT * FROM materialstock")
    List<ViewAdminMaterials> getAllMaterials();

}
