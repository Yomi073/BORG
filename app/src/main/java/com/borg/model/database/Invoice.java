package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice")
public class Invoice {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "quantity")
    public Double quantity;
    @ColumnInfo(name = "sellingPrice")
    public Double sellingPrice;
    @ColumnInfo(name = "sum")
    public Double sum;

}
