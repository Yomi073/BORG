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

    @Query("DELETE FROM materialstock WHERE materialstock.id = :id")
    void deleteMaterialId(Integer id);

    @Query("SELECT * FROM materialstock")
    List<MaterialStock> getAllMaterials();

    @Query("INSERT INTO materialstock (name, quantity, purchasePrice, sellingPrice) VALUES (:name, :quantity, :purchasePrice, :sellingPrice)")
    void insertNewMaterialStock(String name, Double quantity, Double purchasePrice, Double sellingPrice);

}
