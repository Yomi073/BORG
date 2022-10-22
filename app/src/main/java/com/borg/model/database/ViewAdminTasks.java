package com.borg.model.database;

import java.sql.Date;

public class ViewAdminTasks {

    private Integer id;
    private Double pauseLength;
    private Date date;
    private Long startTime;
    private Long endTime;
    private Integer user_FK;
    private Integer client_FK;

    public ViewAdminTasks(Integer id, Double pauseLength, Date date, Long startTime, Long endTime, Integer user_FK, Integer client_FK) {
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

    public Date getDate() {
        return date;
    }

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
