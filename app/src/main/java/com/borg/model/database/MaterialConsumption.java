package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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

}
