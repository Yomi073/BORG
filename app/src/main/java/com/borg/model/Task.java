package com.borg.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "task", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id",childColumns = "user_FK", onDelete = 5), @ForeignKey(entity = Client.class, parentColumns = "id",childColumns = "client_FK", onDelete = 5)})
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public int id;
    @ColumnInfo(name = "pauseLength")
    public Double pauseLength;
    @ColumnInfo(name = "date")
    public Date date;
    @ColumnInfo(name = "startTime")
    public Long startTime;
    @ColumnInfo(name = "endTime")
    public Long endTime;
    @ColumnInfo(name = "user_FK", index = true)
    public int user_FK;
    @ColumnInfo(name = "client_FK", index = true)
    public int client_FK;

}
