package com.borg.model.database;

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

    @Query("DELETE FROM materialConsumption WHERE id = :id")
    void deleteMaterialConsumptionById(Integer id);

    @Query("SELECT sellingPrice FROM materialstock WHERE id = :id")
    Double getMaterialPriceById(Integer id);

    @Query("INSERT INTO materialconsumption (quantity, materialStock_FK, task_FK) VALUES (:quantity, :materialStock_FK, :task_FK)")
    void insertNewMaterialConsumption(Double quantity, Integer materialStock_FK, Integer task_FK);

    @Query("SELECT materialconsumption.id AS indeks, " +
            "materialconsumption.materialStock_FK AS id_on_stock, " +
            "materialconsumption.task_FK AS id_task, " +
            "materialstock.name AS material_name, " +
            "materialconsumption.quantity AS quantity_on_invoice, " +
            "materialstock.quantity AS quantity_on_stock, " +
            "materialstock.purchasePrice AS purchasePrice, " +
            "materialstock.sellingPrice AS sellingPrice, " +
            "(materialconsumption.quantity * materialstock.sellingPrice) AS sum " +
            "FROM materialconsumption LEFT JOIN materialstock ON materialconsumption.materialStock_FK = materialstock.id LEFT JOIN task ON materialconsumption.task_FK = task.id WHERE materialconsumption.task_FK = :id")
    List<ViewInvoice> getInvoiceByTaskID(Integer id);

    @Query("SELECT materialconsumption.id AS indeks, " +
            "materialconsumption.materialStock_FK AS id_on_stock, " +
            "materialconsumption.task_FK AS id_task, " +
            "materialstock.name AS material_name, " +
            "materialconsumption.quantity AS quantity_on_invoice, " +
            "materialstock.quantity AS quantity_on_stock, " +
            "materialstock.purchasePrice AS purchasePrice, " +
            "materialstock.sellingPrice AS sellingPrice, " +
            "(materialconsumption.quantity * materialstock.sellingPrice) AS sum " +
            "FROM materialconsumption LEFT JOIN materialstock ON materialconsumption.materialStock_FK = materialstock.id LEFT JOIN task ON materialconsumption.task_FK = task.id")
    List<ViewInvoice> getAllInvoice();

    @Query("INSERT INTO materialconsumption (task_FK, materialStock_FK, quantity) VALUES(:task_id, :materialStock_id, :quantity)")
    void insertNewMaterialIntoTaskByTaskFK(Integer task_id,Integer materialStock_id,Double quantity);

}
