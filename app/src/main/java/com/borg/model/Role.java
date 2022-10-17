package com.borg.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "role")
public class Role {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;

}
