package com.borg.model.database;

import androidx.room.TypeConverters;

import com.borg.model.TimestampConverter;

import java.util.Date;

public class ViewUserTasks {

    private Integer task_id;
    @TypeConverters({TimestampConverter.class})
    private Date task_date;
    private Integer user_id;
    private Integer client_id;
    private String user_firstName;
    private String client_firstName;
    private String client_address;
    private String client_phoneNumber;

    public ViewUserTasks(Integer task_id, Date task_date, Integer user_id, Integer client_id, String user_firstName, String client_firstName, String client_address, String client_phoneNumber) {
        this.task_id = task_id;
        this.task_date = task_date;
        this.user_id = user_id;
        this.client_id = client_id;
        this.user_firstName = user_firstName;
        this.client_firstName = client_firstName;
        this.client_address = client_address;
        this.client_phoneNumber = client_phoneNumber;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    @TypeConverters({TimestampConverter.class})
    public Date getTask_date() {
        return task_date;
    }

    @TypeConverters({TimestampConverter.class})
    public void setTask_date(Date task_date) {
        this.task_date = task_date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getUser_firstName() {
        return user_firstName;
    }

    public void setUser_firstName(String user_firstName) {
        this.user_firstName = user_firstName;
    }

    public String getClient_firstName() {
        return client_firstName;
    }

    public void setClient_firstName(String client_firstName) {
        this.client_firstName = client_firstName;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getClient_phoneNumber() {
        return client_phoneNumber;
    }

    public void setClient_phoneNumber(String client_phoneNumber) {
        this.client_phoneNumber = client_phoneNumber;
    }
}
