package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Entity(tableName = "materialconsumption", foreignKeys = {@ForeignKey(entity = MaterialStock.class, parentColumns = "id",childColumns = "materialStock_FK", onDelete = 5), @ForeignKey(entity = Task.class, parentColumns = "id",childColumns = "task_FK", onDelete = 5)})
public class MaterialConsumption {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "quantity")
    public String quantity;
    @ColumnInfo(name = "materialStock_FK", index = true)
    public Integer materialStock_FK;
    @ColumnInfo(name = "task_FK", index = true)
    public Integer task_FK;

    public MaterialConsumption(Integer id, String quantity, Integer materialStock_FK, Integer task_FK) {
        this.id = id;
        this.quantity = quantity;
        this.materialStock_FK = materialStock_FK;
        this.task_FK = task_FK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getMaterialStock_FK() {
        return materialStock_FK;
    }

    public void setMaterialStock_FK(Integer materialStock_FK) {
        this.materialStock_FK = materialStock_FK;
    }

    public Integer getTask_FK() {
        return task_FK;
    }

    public void setTask_FK(Integer task_FK) {
        this.task_FK = task_FK;
    }
}
