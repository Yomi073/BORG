package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.borg.model.TimestampConverter;

import java.util.Date;

@Entity(tableName = "task", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id",childColumns = "user_FK", onDelete = 5), @ForeignKey(entity = Client.class, parentColumns = "id",childColumns = "client_FK", onDelete = 5)})
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "pauseLength")
    public Double pauseLength;
    @ColumnInfo(name = "date")
    @TypeConverters({TimestampConverter.class})
    public Date date;
    @ColumnInfo(name = "startTime")
    public Long startTime;
    @ColumnInfo(name = "endTime")
    public Long endTime;
    @ColumnInfo(name = "user_FK", index = true)
    public Integer user_FK;
    @ColumnInfo(name = "client_FK", index = true)
    public Integer client_FK;

}
