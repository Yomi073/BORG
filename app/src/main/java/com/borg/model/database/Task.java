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


    public Task(Task task) {
        this.id = task.getId();
        this.pauseLength = task.getPauseLength();
        this.date = task.getDate();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
        this.user_FK = task.getUser_FK();
        this.client_FK = task.getClient_FK();
    }

    public Task(Integer id, Double pauseLength, Date date, Long startTime, Long endTime, Integer user_FK, Integer client_FK) {
        this.id = id;
        this.pauseLength = pauseLength;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user_FK = user_FK;
        this.client_FK = client_FK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPauseLength() {
        return pauseLength;
    }

    public void setPauseLength(Double pauseLength) {
        this.pauseLength = pauseLength;
    }

    @TypeConverters({TimestampConverter.class})
    public Date getDate() {

        return date;
    }

    @TypeConverters({TimestampConverter.class})
    public void setDate(Date date) {
        this.date = date;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getUser_FK() {
        return user_FK;
    }

    public void setUser_FK(Integer user_FK) {
        this.user_FK = user_FK;
    }

    public Integer getClient_FK() {
        return client_FK;
    }

    public void setClient_FK(Integer client_FK) {
        this.client_FK = client_FK;
    }
}
