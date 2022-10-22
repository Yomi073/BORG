package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materialstock")
public class MaterialStock {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "quantity")
    public Double quantity;
    @ColumnInfo(name = "purchasePrice")
    public Double purchasePrice;
    @ColumnInfo(name = "sellingPrice")
    public Double sellingPrice;

}
